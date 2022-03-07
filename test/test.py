import time
import unittest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

class Test(unittest.TestCase):
    BASE_URL = "http://localhost:8080"
    VIEW_APPLICATIONS_PAGE = "/html/body/main/section/div/h1"
    APPLY_PAGE = "/html/body/main/div/h1"
    DEBUG = False

    def setUp(self):
        self.driver = webdriver.Chrome()

    def __login(self, username, password, driver):
        username_form = driver.find_element(By.XPATH, '//*[@id="login-username"]');
        password_form = driver.find_element(By.XPATH, '//*[@id="login-password"]');
        submit_form = driver.find_element(By.XPATH, '/html/body/main/div/form/p/input')
        username_form.send_keys(username)
        password_form.send_keys(password)
        submit_form.click()

    def __login_recruiter(self, driver):
        self.__login("admin", "123456", driver)


    def __find(self, xpath, driver):
        return driver.find_element(By.XPATH, xpath)

    # RECRUITER RELATED REQUIREMENTS
    def test_login_recruiter_redirect(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_recruiter(driver)
        header = self.__find(self.VIEW_APPLICATIONS_PAGE, driver)
        if self.DEBUG:
            time.sleep(3)
        self.assertIn("Applicants", header.text)

    def test_recruiter_applicants_next_page(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_recruiter(driver)
        next_button = self.__find("/html/body/main/section/div/a", driver)
        href = next_button.get_attribute("href")
        if self.DEBUG:
            time.sleep(3)
        self.assertIn("/recruitment?page=1", href)

    def test_recruiter_applicants_previous_button_on_first_page(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_recruiter(driver)
        if self.DEBUG:
            time.sleep(3)
        previous_button = self.__find("/html/body/main/section/div/a[1]", driver)
        if previous_button.text == "previous":
            self.fail("should not exist on first page.")

    # APPLICANT RELATED REQUIREMENTS
    def __login_applicant(self, driver):
        self.__login("zven", "123", driver)

    def test_login_applicant_redirect(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_applicant(driver)
        header = self.__find(self.APPLY_PAGE, driver)
        if self.DEBUG:
            time.sleep(3)
        self.assertIn("Apply", header.text)

    def test_apply_validation(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_applicant(driver)
        driver.get(self.BASE_URL + "/apply")
        self.__find_and_click("/html/body/main/div/form/button[1]", driver)
        validationMessage = self.__find("/html/body/main/div/form/div/div[2]/span", driver)
        self.assertIn("Years of experience must be a positive number", validationMessage.text)

    def test_apply_form_duplicated(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_applicant(driver)
        driver.get(self.BASE_URL + "/apply")
        self.__find_and_input('//*[@id="application-yearsOfExperience"]', "12", driver)
        self.__find_and_click("/html/body/main/div/form/button[1]", driver)
        self.__find_and_input('//*[@id="application-yearsOfExperience"]', "12", driver)
        self.__find_and_click("/html/body/main/div/form/button[1]", driver)
        validationMessage = self.__find('/html/body/main/div/div/span', driver)
        self.assertIn("Error, you can not add the same competence twice", validationMessage.text)

    def test_apply(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_applicant(driver)
        driver.get(self.BASE_URL + "/apply")
        self.__find_and_input('//*[@id="application-yearsOfExperience"]', "12", driver)
        self.__find_and_click("/html/body/main/div/form/button[1]", driver)
        self.__find_and_click('//*[@id="application-competence"]/option[2]', driver)
        self.__find_and_click("/html/body/main/div/form/button[1]", driver)
        ticketLabel = self.__find("/html/body/main/div/form/section/ul/label", driver)
        ticketSpan = self.__find('/html/body/main/div/form/section/ul/label/span[1]', driver)
        lotteriesLabel = self.__find('/html/body/main/div/form/section/ul[2]/label', driver)
        lotteriesSpan = self.__find('/html/body/main/div/form/section/ul[2]/label/span[1]', driver)
        self.assertIn("competence-to-be-removed", ticketLabel.get_attribute("for"), msg="cannot find newly added competence")
        self.assertIn("competence-to-be-removed", lotteriesLabel.get_attribute("for"), msg="cannot find newly added competence")
        self.assertIn("ticket sales", ticketSpan.text)
        self.assertIn("lotteries", lotteriesSpan.text)
        ticketCheckbox = '/html/body/main/div/form/section/ul[1]/input[1]'
        lotteriesCheckbox = '/html/body/main/div/form/section/ul[2]/input[1]'
        removeButton = '/html/body/main/div/form/button[2]'
        self.__remove_competence([ticketCheckbox, lotteriesCheckbox], removeButton, driver)

    def test_apply_remove(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_applicant(driver)
        driver.get(self.BASE_URL + "/apply")
        self.__find_and_input('//*[@id="application-yearsOfExperience"]', "12", driver)
        self.__find_and_click("/html/body/main/div/form/button[1]", driver)
        label = self.__find("/html/body/main/div/form/section/ul/label", driver)
        saveButton = self.__find_and_click('/html/body/main/div/form/button[3]', driver)
        label = self.__find("/html/body/main/div/form/section/ul/label", driver)
        self.assertIn("competence-to-be-removed", label.get_attribute("for"), msg="cannot find newly added competence")
        checkbox = '//*[@id="competence-to-be-removed"]'
        removeButton = '/html/body/main/div/form/button[2]'
        self.__remove_competence([checkbox], removeButton, driver)
        try:
            label = self.__find("/html/body/main/div/form/section/ul/label", driver)
            self.fail("label should not exist")
        except:
            pass

    def __remove_competence(self, checkboxes, removeButton, driver):
        for checkbox in checkboxes:
            self.__find_and_click(checkbox, driver)
        self.__find_and_click(removeButton, driver)

    # SIGNUP RELATED REQUIREMENTS
    def __signup(self, fn, sn, un, pnr, em, pa, cpa, driver):
        firstname_form = self.__find('//*[@id="create-account-firstname"]', driver)
        surname_form = self.__find('//*[@id="create-account-surname"]', driver)
        username_form = self.__find('//*[@id="create-account-username"]', driver)
        pnr_form = self.__find('//*[@id="create-account-pnr"]', driver)
        email_form = self.__find('//*[@id="create-account-email"]', driver)
        password_form = self.__find('//*[@id="create-account-password"]', driver)
        confirmpassword_form = self.__find('//*[@id="create-account-confirm-password"]', driver)
        submit_button = self.__find('//*[@id="createAccountForm"]/input[2]', driver)
        firstname_form.send_keys(fn)
        surname_form.send_keys(sn)
        username_form.send_keys(un)
        pnr_form.send_keys(pnr)
        email_form.send_keys(em)
        password_form.send_keys(pa)
        confirmpassword_form.send_keys(cpa)
        submit_button.click()

    def __retrieve_fields(self, driver):
        firstname_form = self.__find('//*[@id="create-account-firstname"]', driver)
        surname_form = self.__find('//*[@id="create-account-surname"]', driver)
        username_form = self.__find('//*[@id="create-account-username"]', driver)
        pnr_form = self.__find('//*[@id="create-account-pnr"]', driver)
        email_form = self.__find('//*[@id="create-account-email"]', driver)
        password_form = self.__find('//*[@id="create-account-password"]', driver)
        confirmpassword_form = self.__find('//*[@id="create-account-confirm-password"]', driver)
        submit_button = self.__find('//*[@id="createAccountForm"]/input[2]', driver)


    def test_signup_is_signup_page(self):
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        header = self.__find("/html/body/main/div/h1", driver)
        self.assertIn("Signup", header.text)

    def test_signup_failure_invalid_username(self):
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        firstname = "test"
        self.__signup(firstname, "will", "",  "19990115-0000", "fail@gmail.com", "123456", "123456", driver)
        validationMessage = self.__find("/html/body/main/div/form/div/span", driver)
        firstname_form = self.__find('//*[@id="create-account-firstname"]', driver)
        self.assertIn("Username must be", validationMessage.text)
        self.assertIn(firstname, firstname_form.get_attribute("value"))

    def test_signup_failure_all_invalid_fields(self):
        # TODO
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        self.__signup("j", "j", "j",  "02-00", "notgmail@adf", "1", "9", driver)
        firstname_validation = self.__find('/html/body/main/div/form/div/div/div[1]/span', driver)
        surname_validation = self.__find('/html/body/main/div/form/div/div/div[2]/span', driver)
        username_validation = self.__find('/html/body/main/div/form/div/span[1]', driver)
        pnr_validation = self.__find('/html/body/main/div/form/div/span[2]', driver)
        password_validation = self.__find('/html/body/main/div/form/div/span[3]', driver)
        confirmpassword_validation= self.__find('/html/body/main/div/form/div/span[4]', driver)
        self.assertIn("Firstname must be between 2 - 30 characters", firstname_validation.text)
        self.assertIn("Surname must be between 2 - 30 characters", surname_validation.text)
        self.assertIn("Username must be between 5 - 30 characters", username_validation.text)
        self.assertIn("Personal number must consist of 10 or 12 numbers (YY)YYMMDD-XXXX", pnr_validation.text)
        self.assertIn("Password must be between 6 - 18 characters", password_validation.text)
        self.assertIn("Password must be between 6 - 18 characters", confirmpassword_validation.text)


    # obs! konto måste tas bort...
    def test_signup_success(self):
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        username = "toremove"
        self.__signup("testing", "testing", username,  "20010100-0000", "success@gmail.com", "123456", "123456", driver)
        header = self.__find("/html/body/main/div/h1", driver)
        self.assertIn("Login", header.text)
        self.__login(username, "123456", driver)
        loggedInHeader = self.__find(self.APPLY_PAGE, driver)
        self.assertIn("Apply", loggedInHeader.text)
        driver = self.driver
        self.__remove_user(username, driver)


    def __find_and_input(self, xpath, content, driver):
        element = self.__find(xpath, driver)
        element.clear()
        element.send_keys(content)
        return element

    def __find_and_click(self, xpath, driver):
        element = self.__find(xpath, driver)
        element.click()
        return element

    # adminer DB
    def __remove_user(self, username, driver):
        driver.get("http://localhost:8050/?pgsql=db&username=postgres&db=recruitment&ns=public&select=person&order%5B0%5D=person_id&desc%5B0%5D=1")
        self.__find_and_input('//*[@id="username"]', "postgres", driver)
        self.__find_and_input('//*[@id="content"]/form/table/tbody/tr[4]/td/input', "root123", driver)
        self.__find_and_click('//*[@id="content"]/form/p/input', driver)
        checkbox = self.__find_and_click('/html/body/div[2]/form[2]/div[1]/table/tbody/tr[1]/td[1]/input', driver)
        deleteButton = self.__find_and_click('//*[@id="content"]/form[2]/div[2]/div/fieldset[4]/div/input[3]', driver)
        alert = driver.switch_to.alert
        alert.accept()

    def test_signup_user_already_exists(self):
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        self.__signup("admin", "admin", "admin", "20010100-0000", "admin@gmail.com", "123456", "123456", driver)
        header = self.__find("/html/body/main/div/h1", driver)
        if self.DEBUG:
            time.sleep(4)
        self.assertIn("Person already exists", header.text)

    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()
