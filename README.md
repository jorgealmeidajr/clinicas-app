# Aplicativo Gerenciador de Clínicas

## Introdução
Este sistema foi desenvolvido como trabalho de conclusão na disciplina **ST SADS 2016/1 N1 - Programação para web**.

Este aplicativo é um trabalho feito no SENAI SC Florianópolis, na terceira fase do curso de Análise de Sistemas(2017/1), na disciplina de **Programação web** ministrada pelo professor **Paulo Roberto Bueno**.

## Arquitetura da aplicação
A aplicação foi implementada com Java 8 e roda em um servidor de aplicação JEE7 (WildFly Full 10.0.0.Final). As principais tecnologias usadas no frontend foram JSF, Facelets e Primefaces 6.1. As principais tecnologias usadas no backend foram o JPA e o CDI. A aplicação é uma SPA em que temos apenas uma página index que fica atualizando seu conteúdo de acordo com os eventos iniciados pelo usuário.

O projeto deve ser aberto no Eclipse, ele é um Dynamic Web Project.

O banco de dados usado foi o MySQL. Para criar o banco de dados use o arquivo de dump que possui a estrutura e alguns dados de teste e que foi colocado no diretorio **db/mysql** disponivel nesse repositorio. A imagem abaixo representa o banco implementado com suas tabelas e seus relacionamentos.

![clinicas-app-v01-db](https://user-images.githubusercontent.com/6424524/29288074-da7d1750-810d-11e7-9104-6d1292b909f1.png)

## Screenshots da aplicação
A imagem abaixo mostra a pagina inicial da aplicacao.

![clinicas-app-v01-index](https://user-images.githubusercontent.com/6424524/29288211-500854a8-810e-11e7-935a-0e6268a68b40.png)

Na página inicial é possível fazer uma busca de todos os médicos cadastrados na aplicação. Essa funcionalidade fica pública e não necessita de autenticação. È possível filtrar a busca pelo nome do médico, especialidade e cidade onde o médico atende. Se o usuário não estiver cadastrado no sistema, ele pode ir em no link "Quero me cadastrar" situado no topo à direita.

A imagem abaixo mostra o formulario de edicao de dados do usuario.

![clinicas-app-v01-usuario](https://user-images.githubusercontent.com/6424524/29288239-6a66afac-810e-11e7-86bd-114f342a9b29.png)

A imagem abaixo mostra a tela de listagem de medicos.

![clinicas-app-v01-medicos](https://user-images.githubusercontent.com/6424524/29288257-77e3daf6-810e-11e7-868d-da2d57516295.png)

A imagem abaixo mostra a tela de listagem de clinicas.

![clinicas-app-v01-clinicas](https://user-images.githubusercontent.com/6424524/29288278-892e51ce-810e-11e7-9a18-7c1419f7177f.png)

## Configuração do Wildfly
Os passos para as configurações do servidor Wildfly são os seguintes:

1 – Acesse o diretório $WILDFLY_HOME/modules/system/layers/base/com.
2 – Uma vez lá, crie o diretório mysql e seu subdiretório main.
3 – Dentro de main coloque o jar do driver JDBC do MySQL.
4 – Agora crie um arquivo chamado module.xml e dentro dele adicione as seguintes linhas de código:

```
<?xml version="1.0" encoding="UTF-8"?>

<module xmlns="urn:jboss:module:1.3" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.43-bin.jar" />
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```

5 – Agora acesse o diretório $WILDFLY_HOME/standalone/configuration e abra o arquivo standalone.xml.
6 – Procure pela tag **<datasources />**. Você encontrará um datasource pré-configurado para o banco de dados H2 como exemplo. Abaixo dele crie seu próprio datasource seguindo o exemplo abaixo:

```
<datasource 
    jta="true" 
    jndi-name="java:jboss/datasources/clinicasDS" pool-name="clinicasDS" 
    enabled="true" use-java-context="true" use-ccm="true">
    <connection-url>jdbc:mysql://localhost:3306/clinicasdb?useSSL=false</connection-url>
    <driver>mysql</driver>
    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
    <pool>
        <min-pool-size>10</min-pool-size>
        <max-pool-size>100</max-pool-size>
        <prefill>true</prefill>
    </pool>
    <security>
        <user-name>root</user-name>
        <password></password>
    </security>
    <statement>
        <prepared-statement-cache-size>32</prepared-statement-cache-size>
        <share-prepared-statements>true</share-prepared-statements>
    </statement>
</datasource>
```

7 – Logo abaixo do seu datasource você deve encontrar uma tag chamada **<drivers />**, dentro dela copie e cole o seguinte código:

```
<driver name="mysql" module="com.mysql">
  <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
</driver>
ou
<driver name="mysql" module="com.mysql">
  <driver-class>com.mysql.jdbc.Driver</driver-class>
</driver>
```

## Melhorias/Correções a serem implementadas
Se eu estiver com a página aberta, reinicio o servidor e não dou refresh no browser, então ocorre uma exceção:
javax.faces.application.ViewExpiredException: viewId:/index.xhtml - A exibição de /index.xhtml não pôde ser restaurada. 
O problema é que nenhuma mensagem aparece para o usuário e a aplicação não funciona até dar um refresh.

Quanto à arquitetura, a aplicação poderia ser implementada em Spring para remover a dependencia com um servidor de aplicacao. Seria removido a vinculacao com o CDI e aplicacao poderia rodar num Tomcat ou num Jetty.

O projeto hoje é um Dynamic Web Project e deve ser aberto no Eclipse. Devo migrar para o Maven e remover essa dependencia.

Ficou faltando aplicar index nas colunas usadas nas pesquisas. Hoje o sistema suporta poucas linhas mas precisa ser otimizado caso haja muitas linhas no banco de dados.
O email do usuario, cpf do médico, numero do crm do medico, cnpj da clinica.

As fotos dos médicos deverão ser salvas em um diretorio no servidor e não numa coluna do banco de dados.

Hoje só posso vincular uma clinica a um medico. Preciso criar uma funcionalidad que vincule um medico a varias clinicas?

Ficou faltando implementar uma rotina que busque o endereco de acordo com o CEP no momento do cadastro de uma clinica.

Ficou faltando criar perfis que restrinjam o acesso aos recursos na aplicacao. Hoje tudo é visto, basta se autenticar.