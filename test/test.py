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

    def test_apply(self):
        driver = self.driver
        driver.get(self.BASE_URL)
        self.__login_applicant(driver)



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

    def test_signup_is_signup_page(self):
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        header = self.__find("/html/body/main/div/h1", driver)
        self.assertIn("Signup", header.text)

    def test_signup_failure_empty_username_but_fields_remain(self):
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        firstname = "test"
        self.__signup(firstname, "will", "",  "19990115-0000", "fail@gmail.com", "123456", "123456", driver)
        validationMessage = self.__find("/html/body/main/div/form/div/span", driver)
        firstname_form = self.__find('//*[@id="create-account-firstname"]', driver)
        self.assertIn("Username must be", validationMessage.text)
        self.assertIn(firstname, firstname_form.get_attribute("value"))

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

    def __remove_user(self, username, driver):
        driver.get("http://localhost:8050/?pgsql=db&username=postgres&db=recruitment&ns=public&select=person&order%5B0%5D=person_id&desc%5B0%5D=1")
        self.__find_and_input('//*[@id="username"]', "postgres", driver)
        self.__find_and_input('//*[@id="content"]/form/table/tbody/tr[4]/td/input', "root123", driver)
        self.__find_and_click('//*[@id="content"]/form/p/input', driver)
        checkbox = self.__find_and_click('/html/body/div[2]/form[2]/div[1]/table/tbody/tr[1]/td[1]/input', driver)
        deleteButton = self.__find_and_click('//*[@id="content"]/form[2]/div[2]/div/fieldset[4]/div/input[3]', driver)
        alert = driver.switch_to.alert
        alert.accept()

    # admin, 123456 ska redan finnas
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
