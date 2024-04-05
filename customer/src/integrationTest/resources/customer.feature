Feature: A customer is retrieved ok

  Scenario: we make a call to retrieve a valid customer
    When we retrieve customer "c2c5ad6c-28a4-4ce3-a58f-9f2073e0a5b3"
    Then we have retrieved customer with name Harry