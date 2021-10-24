# JasEx api (Comentado para fins de estudo)

### Aplicação de gerenciamento de entregas.


### Links e aprendizado

#### quer json, xml ou o que ? <br>
Respondendo de forma customizada a requisição do cliente<br>
usando: jackson<br>
dependencia: <br>
```xml
<dependency>
  <groupId>com.fasterxml.jackson.dataformat</groupId>
  <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```
**Obs:** O spring usa por padrão o jackson, para converter para json...
<br>Após instalado, o usuário poderá escolher entre 
pedir uma resposta em json ou xml...
*header: Accept application/xml*

**Atenção:** Eu removi das dependencias, pois json é blimblim... :) 

#### Mantendo o banco de dados concreto com o flywaydb

O flywaydb nos proporciona criar migrações, isso é bom para gerenciar.<br>
dependencia: <br>
```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
</dependency>
```

será necessário criar pastas em resources;
db/migration

**Atenção:** jakartar é uma especificação, hibernates é uma implementação e spring data jpa uma biblioteca.

codigos http: https://httpstatuses.com/


#### Boas Praticas
trabalhando com tempo em:<br>
**iso 8061** horas em padrão internacional.<br>

É necessário, pois garante informações que ajudam no tratamento, tipo: O local do horário.<br>

**Como faz?** Use a classe OffSetDateTime, do pacote 
java.time, do java.

##### Usando DTO

biblioteca: modelmapper<br>
link: http://modelmapper.org/getting-started/<br>

dependencia:<br> 
```xml
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>2.4.2</version>
</dependency>
```<br>
faz: Transforma model em dto e dto em model, semelhante ao mapstruct
