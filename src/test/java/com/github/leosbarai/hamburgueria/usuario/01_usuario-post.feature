Feature: Teste a API de usuário POST /usuarios

  Background:
    * url 'http://localhost:8080/usuarios'

    * def config = { username: 'sa', password: '', url: 'jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1', driverClassName: 'org.h2.Driver' }
    * def DbUtils = Java.type('com.github.leosbarai.hamburgueria.utils.DbUtils')
    * def db = new DbUtils(config)

  Scenario: Teste do cadastro do usuário
    * def body = read('user_post.json')

    Given request body
    When method POST
    Then status 201

#    * def user = db.readRows('SELECT * FROM USUARIO')
#    * match user contains { id: '#(id)', nome: 'José Raimundo Silva', email: 'raimundinho@email.com' }
