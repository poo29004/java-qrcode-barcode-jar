[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

# Como gerar arquivo `.jar` executável

O principal objetivo desse repositório consiste em demonstrar como gerar uma aplicação Java empacotada, com todas as bibliotecas que depende, em um único arquivo `.jar` por meio do [Gradle Shadow plugin](https://imperceptiblethoughts.com/shadow/).

## Gerando arquivo `.jar`

Para gerar um arquivo `. jar` executável desse projeto basta executar a tarefa gradle `shadowJar`. Isso pode ser feito no IntelliJ a partir do painel `Gradle` ou diretamente no terminal com o aplicativo `gradle`.

```shell
./gradlew shadowJar
```

O arquivo `app-all.jar` será gerado dentro do diretório `app/build/libs`.



## Executando a aplicação para gerar um QRCode e um BarCode

Essa aplicação exemplo gera códigos de barra e QRCode em arquivos `.png`. A aplicação, quando empacotada em um arquivo `.jar` executável, poderá ser executada no terminal da seguinte maneira:

```shell
java -jar app-all.jar barcode 123456 imagem.png

# ou

java -jar app-all.jar qrcode 'https://www.sj.ifsc.edu.br' imagem.png
```



## Adicionando um arquivo JAR como dependência do projeto

Nesse projeto foi criado um diretório `libs` para armazenar o arquivo `.jar` da biblioteca [barcode](libs/barcode.jar).

Foi necessário modificar o arquivo [`.gitignore`](.gitignore) de forma a garantir que os arquivos `.jar` dentro do diretório `libs` não sejam ignorados. Sendo assim, foi incluída a linha abaixo no final do arquivo [`.gitignore`](.gitignore).   

```
!libs/*.jar
```

 E no arquivo [`build.gradle`](app/build.gradle) foi incluída uma linha na seção de dependências. 

```groovy
dependencies {
    
    // importando arquivo JAR que está dentro do diretório lib
    implementation files('libs/barcode.jar')
}
```

## Adicionando biblioteca de repositório online como dependência do projeto

Para gerar QRCode foi feito uso das bibliotecas [Google ZXing Core](https://mvnrepository.com/artifact/com.google.zxing/core) e [Google ZXing Java SE Extensions](https://mvnrepository.com/artifact/com.google.zxing/javase), as quais estão disponíveis no repositório [MVN Repository](https://mvnrepository.com). 

As respectivas linhas foram incluídas no arquivo [`build.gradle`](app/build.gradle).

```groovy
dependencies {
    implementation 'com.google.zxing:core:3.5.3'

    implementation 'com.google.zxing:javase:3.5.3'
}
```



### Gradle Shadow plugin

Foi feito uso do [Gradle Shadow plugin](https://imperceptiblethoughts.com/shadow/) para empacotar toda a aplicação Java,  junto com suas dependências, dentro de um arquivo `.jar`. É necessário indicar qual o nome da classe principal, aquela com o método `public static void main(String[] args)`, pois é essa que será executada. Neste exemplo também foi feito uso do plugin gradle `application`.

Nesse projeto, tal classe é `poo.Principal`.  Sendo assim, foi necessário incluir as seguintes linhas no  arquivo [`build.gradle`](app/build.gradle). 

```groovy
plugins {
    id 'application'
    // Gradle Shadow plugin
    id 'com.github.johnrengelman.shadow' version '8.1.1'
}

application{
	// informar o nome do pacote e classe Java que tem o método main
	mainClass = 'poo.Principal'
}
```


### Para atualizar para a última versão do gradle

Veja documentação oficial que está disponível em https://docs.gradle.org/current/userguide/upgrading_version_8.html
 
```bash
gradle wrapper --gradle-version latest
```