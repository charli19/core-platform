Feature: Price tests for ZARA - Product 35455

  Background:
    * def baseUrl = karate.properties['baseUrl']
    * def apiPath = '/api/v1/prices'
    * url baseUrl + apiPath

  Scenario: Price for product 35455 from ZARA on 2020-06-14 at 10:00
    Given param brandId = 1
    And param productId = 35455
    And param applicationDate = '2020-06-14T10:00:00+02:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 35.50
    And match response.priceList == 1
    And match response.startDate == '2020-06-14T00:00:00+02:00'
    And match response.endDate == '2020-12-31T23:59:59+01:00'

  Scenario: Price for product 35455 from ZARA on 2020-06-14 at 16:00
    Given param brandId = 1
    And param productId = 35455
    And param applicationDate = '2020-06-14T16:00:00+02:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 25.45
    And match response.priceList == 2
    And match response.startDate == '2020-06-14T15:00:00+02:00'
    And match response.endDate ==  '2020-06-14T18:30:00+02:00'

  Scenario: Price for product 35455 from ZARA on 2020-06-14 at 21:00
    Given param brandId = 1
    And param productId = 35455
    And param applicationDate = '2020-06-14T21:00:00+02:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 35.50
    And match response.priceList == 1
    And match response.startDate == '2020-06-14T00:00:00+02:00'
    And match response.endDate == '2020-12-31T23:59:59+01:00'

  Scenario: Price for product 35455 from ZARA on 2020-06-15 at 10:00
    Given param brandId = 1
    And param productId = 35455
    And param applicationDate = '2020-06-15T10:00:00+02:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 30.50
    And match response.priceList == 3
    And match response.startDate == '2020-06-15T00:00:00+02:00'
    And match response.endDate == '2020-06-15T11:00:00+02:00'

  Scenario: Price for product 35455 from ZARA on 2020-06-16 at 21:00
    Given param brandId = 1
    And param productId = 35455
    And param applicationDate = '2020-06-16T21:00:00+02:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 38.95
    And match response.priceList == 4
    And match response.startDate == '2020-06-15T16:00:00+02:00'
    And match response.endDate == '2020-12-31T23:59:59+01:00'