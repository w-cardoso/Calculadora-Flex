<h1>Calculadora Flex</h1>
O cálculo do rendimento do carro é importante, pois o motorista poderá verificar também qual combustível é mais econômico em função do preço na bomba.
Existe a convenção de que o etanol é mais econômico se custar até 70% do preço da gasolina ou 30% mais barato (baseado no teste do Inmetro).
Mas testes atuais realizado pelo Instituto Mauá o motorista poderá verificar que seu carro rende muito mais e então economizará usando etanol mesmo se o percentual estiver acima de 70%, podendo chegar até 75%!

Nesse aplicativo foi utilizado o Firebase, e com ele foi utilizado diversas ferramentas citadas e explicadas abaixo:

<h2>Authentication</h2>
O Firebase Authentication fornece serviços de back-end, SDKs fáceis de usar e bibliotecas de IU prontas para autenticar usuários no seu app. Ele oferece suporte à autenticação por meio de senhas, números de telefone e provedores de identidade federados como Google, Facebook, Twitter e muito mais.
Nesse aplicativo foi utilizado a Autenticação baseada em e-mail e senha.

<h2>RealTime Database</h2>
O Firebase Realtime Database é um banco de dados hospedado na nuvem. Os dados são armazenados como JSON e sincronizados em tempo real com todos os clientes conectados. Quando você cria apps em plataformas cruzadas com nossos SDKs para iOS, Android e JavaScript, todos os clientes compartilham uma instância do Realtime Database e recebem automaticamente atualizações com os dados mais recentes.
Foi utilizado para cadastro de novos usuários. 

<h2>Firebase Cloud Messaging - FCM</h2>
O Firebase Cloud Messaging (FCM) é uma solução de mensagens entre plataformas que permite o envio confiável de notificações sem custo.

<h2>Firebase Remote Config</h2>
É um serviço em nuvem que permite alterar o comportamento e a aparência do aplicativo sem exigir que os usuários baixem uma atualização de aplicativo. Ao usar o Remote config, você cria valores padrão no aplicativo que controlam o comportamento e a aparência do seu aplicativo.

<h2>Firebase Storage</h2>
Com os SDKs do Firebase para Cloud Storage, você usa a segurança do Google para fazer o upload e o download de arquivos nos aplicativos Firebase, independentemente da qualidade da rede.

<h2>Firebase Crashlytics</h2>
O Firebase Crashlytics é uma ferramenta de relatório de falhas leve e em tempo real que ajuda a monitorar, priorizar e corrigir problemas de estabilidade que comprometem a qualidade do seu aplicativo. O Crashlytics economiza tempo na solução de problemas com o agrupamento inteligente das falhas e a exibição das circunstâncias que levam a elas.

<h2>Firebase Analytics</h2>
Os relatórios do Firebase Analytics ajudam a entender claramente como os usuários se comportam, o que permite tomarmos decisões fundamentadas sobre marketing de aplicativos e otimizações de desempenho.

### Pré requisitos

Dependencias necessárias para buildar o projeto.

```
    implementation 'com.google.firebase:firebase-auth:18.1.0'
    implementation 'com.google.firebase:firebase-database:18.0.0'
    implementation 'com.google.firebase:firebase-messaging:19.0.1'
    implementation 'com.google.firebase:firebase-config:18.0.0'
    implementation 'com.google.firebase:firebase-core:17.0.1'
    implementation 'com.google.firebase:firebase-storage:18.1.0'
    implementation "org.jetbrains.anko:anko-commons:$anko_version"
    implementation 'com.google.android.material:material:1.0.0'
    implementation 'com.facebook.stetho:stetho:1.5.1'
    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.crashlytics.sdk.android:crashlytics:2.10.1'
```

<div align="center" class="container">
  <div class="row">
    <img src="https://github.com/w-cardoso/placApp/blob/master/Prints%20Aplicativo/home.png" width="200" height="400"/>
    <img src="https://github.com/w-cardoso/placApp/blob/master/Prints%20Aplicativo/team_home.png" width="200" height="400"/>
    <img src="https://github.com/w-cardoso/placApp/blob/master/Prints%20Aplicativo/placar.png" width="200" height="400"/>
</div>
</div>
