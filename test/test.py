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
        driver.get("http://localhost:8080")
        self.__login_recruiter(driver)
        header = self.__find(self.VIEW_APPLICATIONS_PAGE, driver)
        if self.DEBUG:
            time.sleep(3)
        self.assertIn("Applicants", header.text)

    def test_recruiter_applicants_next_page(self):
        driver = self.driver
        driver.get("http://localhost:8080")
        self.__login_recruiter(driver)
        next_button = self.__find("/html/body/main/section/div/a", driver)
        href = next_button.get_attribute("href")
        if self.DEBUG:
            time.sleep(3)
        self.assertIn("/recruitment?page=1", href)
        previous_button = self.__find("/html/body/main/section/div/a[1]", driver)
        if previous_button.text == "previous":
            self.fail("should not exist on first page.")

    def test_recruiter_applicants_previous_button_on_first_page(self):
        driver = self.driver
        driver.get("http://localhost:8080")
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
        driver.get("http://localhost:8080")
        self.__login_applicant(driver)
        header = self.__find(self.APPLY_PAGE, driver)
        if self.DEBUG:
            time.sleep(3)
        self.assertIn("Apply", header.text)

    # SIGNUP RELATED REQUIREMENTS
    def test_signup_is_signup_page(self):
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        header = self.__find("/html/body/main/div/h1", driver)
        self.assertIn("Signup", header.text)

    def __signup(self, fn, sn, un, pnr, em, pa, cpa, driver):
        firstname_form = self.__find('//*[@id="create-account-firstname"]', driver)
        surname_form = self.__find('//*[@id="create-account-surname"]', driver)
        username_form = self.__find('//*[@id="create-account-firstname"]', driver)
        pnr_form = self.__find('//*[@id="create-account-pnr"]', driver)
        email_form = self.__find('//*[@id="create-account-email"]', driver)
        password_form = self.__find('//*[@id="create-account-password"]', driver)
        confirmpassword_form = self.__find('//*[@id="create-account-confirm-password"]', driver)
        submit_button = self.__find('//*[@id="createAccountForm"]/input[2]', driver)
        firstname_form.send_keys(fn)
        surname_form.send_keys(sn)
        pnr_form.send_keys(pnr)
        email_form.send_keys(em)
        password_form.send_keys(pa)
        confirmpassword_form.send_keys(cpa)
        submit_button.click()

    def test_signup_failure_empty_username_but_fields_remain(self):
        driver = self.driver
        driver.get(self.BASE_URL + "/sign-up")
        firstname = "test"
        self.__signup(firstname, "will", "",  "19990115-0000", "fail@gmail.com", "123456", "123456", driver)
        validationMessage = self.__find("/html/body/main/div/form/div/span", driver)
        firstname_form = self.__find('//*[@id="create-account-firstname"]', driver)
        self.assertIn("Username must be", validationMessage.text)
        self.assertIn(firstname, firstname.get_attribute("value"))


    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()
