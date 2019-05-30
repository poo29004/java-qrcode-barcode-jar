[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE) [![Build Status](https://travis-ci.org/poo29004/java-qrcode-barcode-jar.svg?branch=master)](https://travis-ci.org/poo29004/java-qrcode-barcode-jar)

# Como gerar arquivo `.jar` executável

O principal objetivo desse repositório consiste em demonstrar como gerar uma aplicação Java empacotada, com todas as bibliotecas que depende, em um arquivo `.jar`. Em suma, só é necessário fazer uso do [Gradle Shadow plugin](https://imperceptiblethoughts.com/shadow/) e indicar qual é a classe com método `main` que deverá ser executada. Tudo isso é feito no arquivo [`build.gradle`](build.gradle).

## Gerando arquivo `.jar`

Para gerar um arquivo `. jar` executável desse projeto basta executar a tarefa gradle `shadowJar`. Isso pode ser feito no IntelliJ a partir do painel `Gradle` ou diretamente no terminal com o aplicativo `gradle`.

```shell
gradle shadowJar
```

O arquivo `poo-1.0-all.jar` será gerado dentro do diretório `build/libs`.



## Gerador de QRCode e BarCode

Essa aplicação exemplo gera códigos de barra e QRCode em arquivos `.png`. A aplicação, quando empacotada em um arquivo `.jar` executável, poderá ser executada no terminal da seguinte maneira:

```shell
java -jar poo-1.0-all.jar barcode 123456 imagem.png

# ou

java -jar poo-1.0-all.jar qrcode 'http://www.sj.ifsc.edu.br' imagem.png
```



### Adicionando um arquivo JAR (biblioteca)

Nesse projeto foi criado um diretório `lib` para armazenar o arquivo `.jar` da biblioteca [barcode](lib/barcode.jar).

Foi necessário modificar o arquivo [`.gitignore`](.gitignore) de forma a garantir que os arquivos `.jar` dentro do diretório `lib` não sejam ignorados. Sendo assim, foi incluída a linha abaixo no final do arquivo [`.gitignore`](.gitignore).   

```
!lib/*.jar
```

 E no arquivo [`build.gradle`](build.gradle) foi incluída uma linha na seção de dependências. 

```groovy
dependencies {
    
    // importando arquivo JAR que está dentro do diretório lib
    compile files('lib/barcode.jar')
}
```



### Adicionando biblioteca via dependência do `build.gradle`

Para gerar QRCode foi feito uso das bibliotecas [Google ZXing Core](https://mvnrepository.com/artifact/com.google.zxing) e [Google ZXing Java SE Extensions](https://mvnrepository.com/artifact/com.google.zxing/javase). As respectivas linhas foram incluídas no arquivo [`build.gradle`](build.gradle).

```groovy
dependencies {

    // https://mvnrepository.com/artifact/com.google.zxing/core
    compile group: 'com.google.zxing', name: 'core', version: '3.4.0'

    // https://mvnrepository.com/artifact/com.google.zxing/javase
    compile group: 'com.google.zxing', name: 'javase', version: '3.4.0'
}
```



### Gradle Shadow plugin

Foi feito uso do [Gradle Shadow plugin](https://imperceptiblethoughts.com/shadow/) para empacotar toda a aplicação Java,  junto com suas dependências, dentro de um arquivo `.jar`. É necessário indicar qual o nome da classe principal, aquela com o método `public static void main(String[] args)`, pois é essa que será executada. 

Nesse projeto, tal classe é `poo.Principal`.  Sendo assim, foi necessário incluir as seguintes linhas no  arquivo [`build.gradle`](build.gradle). 

```groovy
plugins {
    // Gradle Shadow plugin
    id 'com.github.johnrengelman.shadow' version '4.0.0'
    id 'java'
}

jar {
    manifest {
        attributes 'Main-Class': 'poo.Principal'
    }
}
```



 
