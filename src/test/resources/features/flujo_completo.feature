Feature: Flujo completo en OrangeHRM

  Scenario: Login exitoso
    Given el usuario esta en la pagina de login
    When inicia sesion con usuario "Admin" y contrasena "admin123"
    Then se muestra el Dashboard

  Scenario: Buscar empleado
    Given el usuario inicio sesion con usuario "Admin" y contrasena "admin123"
    When navega al modulo PIM
    And busca el empleado "John"
    Then se muestran resultados de la busqueda

  Scenario: Flujo completo E2E
    Given el usuario inicio sesion con usuario "Admin" y contrasena "admin123"
    When navega al modulo PIM
    And busca el empleado "John"
    And cierra sesion
    Then se muestra la pagina de login
