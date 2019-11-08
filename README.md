# ProjetoBetha
 Projeto técnico Betha Sistemas
 
# Para começar, você pode simplesmente clonar o repositório Projeto Betha instalar as dependências:

# Pré-requisitos
Você precisa do git para clonar o repositório ProjetoBetha. Você pode obter o git daqui.

para inicializar e testar a semente angular. Você deve ter o Node.js e seu gerenciador de pacotes (npm) instalado. Você pode obtê-los daqui.

# Clone

git clone https://github.com/ViniciusMargotti/ProjetoBetha.git

# cd Front
para rodar Front-end em angularjs


# Instalar dependências
Temos dois tipos de dependências neste projeto: ferramentas e código de estrutura AngularJS. As ferramentas nos ajudam a gerenciar e testar o aplicativo.

	Use npm install


# Execute o aplicativo
O projeto tem um servidor web de desenvolvimento simples. A maneira mais simples de iniciar este servidor é:

# npm start
Agora navegue para o aplicativo em localhost: 9000 / index.html.

# BackEnd
Para o Back end foi construido uma API Spring Boot com uso de APIs Rest e JPA;

para inicia-la basta apenas rodar o projeto Spring no caminho: 

equipamentos-apirest/equipamentos-apirest

IDE Utilizada = Eclipse
Banco de dados: Postgress, as tabelas são geradas pelas entidades, é apenas necessário criar o banco e especifica-lo no arquivo
aplication.properties conforme exemplo a seguir:

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
#Banco local - Vinícius Margotti
spring.datasource.url= jdbc:postgresql://localhost:5432/equipamentos
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
server.port = 8090
