[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

# Como gerar arquivo `.jar` executável

O principal objetivo desse repositório consiste em demonstrar como gerar uma aplicação Java empacotada, com todas as bibliotecas que depende, em um único arquivo `.jar` por meio do [Gradle Shadow plugin](https://imperceptiblethoughts.com/shadow/).

## Configurações no `build.gradle` para usar o Gradle Shadow plugin

Foi feito uso do [Gradle Shadow plugin](https://imperceptiblethoughts.com/shadow/) para empacotar toda a aplicação Java,  junto com suas dependências, dentro de um arquivo `.jar`. 

No arquivo [`build.gradle`](app/build.gradle) é necessário incluir o Gradle Shadow plugin na seção `plugins`.  O *plugin application* exige que indique qual classe Java que contém um método `main` será a classe a ser invocada pela máquina virtual Java. Assim, no trecho abaixo isso foi feito na seção `application`.

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

### Adicionando bibliotecas de terceiros como dependências do projeto

#### Arquivo `.jar` contigo no presente repositório

Nesse projeto foi criado um diretório `libs` para armazenar o arquivo `.jar` da biblioteca [barcode](libs/barcode.jar).

Foi necessário modificar o arquivo [`.gitignore`](.gitignore) de forma a garantir que os arquivos `.jar` dentro do diretório `libs` não sejam ignorados. Sendo assim, foi incluída a linha abaixo no final do arquivo [`.gitignore`](.gitignore).   

```shell
!**/libs/*.jar
```

 E no arquivo [`build.gradle`](app/build.gradle) foi incluída uma linha na seção de dependências. 

```groovy
dependencies {
    
    // importando arquivo JAR que está dentro do diretório lib
    implementation files('libs/barcode.jar')
}
```

#### Biblioteca obtida de repositório online

Para gerar QRCode foi feito uso das bibliotecas [Google ZXing Core](https://mvnrepository.com/artifact/com.google.zxing/core) e [Google ZXing Java SE Extensions](https://mvnrepository.com/artifact/com.google.zxing/javase), as quais estão disponíveis no repositório [MVN Repository](https://mvnrepository.com). 

As respectivas linhas foram incluídas no arquivo [`build.gradle`](app/build.gradle).

```groovy
dependencies {
    implementation 'com.google.zxing:core:3.5.3'

    implementation 'com.google.zxing:javase:3.5.3'
}
```



## Como executar a aplicação por meio da tarefa `run` do gradle

```shell
./gradlew run --args "barcode 12345 codigo.png"
```

Será gerado um arquivo como o nome `codigo.png` dentro do diretório [app](app).


## Empacotando a aplicação com suas dependências em um arquivo `.jar`

Para empacotar a aplicação, bem como suas dependências, dentro de um arquivo `. jar` executável, faça uso da tarefa `shadowJar`.


```shell
./gradlew shadowJar
```

O arquivo `app-all.jar` será gerado dentro do diretório `app/build/libs`.

### Executando a aplicação, a partir do `.jar`, para gerar um QRCode e um BarCode

Essa aplicação exemplo gera códigos de barra e QRCode em arquivos `.png`. A aplicação, quando empacotada em um arquivo `.jar` executável, poderá ser executada no terminal da seguinte maneira:

```shell
java -jar app-all.jar barcode 123456 imagem.png

# ou

java -jar app-all.jar qrcode 'https://www.sj.ifsc.edu.br' imagem.png
```



### Para atualizar para a última versão do gradle

Veja documentação oficial que está disponível em https://docs.gradle.org/current/userguide/upgrading_version_8.html
 
```bash
gradle wrapper --gradle-version latest
```