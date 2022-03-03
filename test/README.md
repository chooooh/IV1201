# Run acceptenace test
## install selenium for Python
- pip install selenium

## download chromedriver
- https://chromedriver.storage.googleapis.com/index.html?path=98.0.4758.102/

## NOTE
The function Test.test_signup_success(self) only works once if there is no functioning Test.__remove_user(self, username, driver) function.
Override and implement your own Test.remove_user(self, username, driver) function.

## run the web server as usual (dev) and run the following script
- py test.py

