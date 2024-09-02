
# Teste Android

![Aiko](imagens/aiko.png)

# **O Desafio**

O projeto foi desenvolvido utilizando a linguagem Kotlin, com a arquitetura MVVM e a biblioteca de injeção de dependência Dagger2. A aplicação foi desenvolvida com o intuito de exibir dados sobre o transporte público da cidade de São Paulo, consultando a [API **Olho Vivo**](api.md) que provê informações em tempo real do monitoramento da frota de ônibus da cidade de São Paulo,  e a API Directions do google map, para obtenção de rotas.
Dos requisitos não essenciais, foram cumpridos:
* **auto-atualização das posições dos veículos**
* **cálculo de rotas de veículos em relação à ultima parada selecionada**
# **Instalação**

Para rodar projeto você precisa:

1. clonar o projeto: ``git clone {caminho}``

2. Abrir a pasta "projeto" pelo android studio

3. Adicionar a chave da API google maps no manifest
4.  Adicionar as chaves do google maps e da API olho vivo em gradle.properties

5. Executar a aplicação em um celular ou emulador

# **Utilização**
A aplicação possui uma única tela, consistindo de:
1. uma mapa interativo, onde pode-se tocar em paradas para obter informações sobre veículos prestes a passar
2. Uma barra de pesquisa com filtros, que permite ao usuário pesquisar informações sobre paradas ou linhas
3. Uma seção maleável de informações, que dinamicamente mostra informações relacionadas a última pesquisa:
    * permite visualização de informações da linha e parada
    * ao tocar um veículo, obtem-se a rota entre a parada e veículo