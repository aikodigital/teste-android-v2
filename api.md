# 🚌 API - Olho vivo

A API **Olho Vivo** fornece **dados em tempo real** sobre a frota de ônibus da cidade de **São Paulo**, permitindo acesso a informações como **localização dos veículos, linhas, paradas e previsões de chegada**.  

## 📡 Acesso à API

Para utilizar a API, siga as instruções do [Guia de Referência da SPTrans](https://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/).  

Após a criação da chave, pode ser necessário aguardar algumas horas para que o uso da API seja permitido.

### 🔑 Obtenção da Chave de Acesso  

1. Acesse a área de **desenvolvedor** da SPTrans:  
   👉 [Meus Aplicativos](https://www.sptrans.com.br/desenvolvedores/perfil-desenvolvedor/meus-aplicativos/)  
2. **Crie um novo aplicativo** e copie a chave gerada.  

   ![Chave API](imagens/chave_api_exemplo.png)  

## 🌎 Proxy AIKO (Correção de CORS)  

Se estiver enfrentando **problemas de CORS** ao consumir a API diretamente, utilize o **proxy da Aiko** para contornar essa restrição:  

```HTML
https://aiko-olhovivo-proxy.aikodigital.io/
```

## 📖 Documentação Oficial

Para informações detalhadas sobre endpoints, parâmetros e formatos de resposta, consulte a documentação oficial da API: [Documentação](http://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/documentacao-api/)

## 🔬 Projeto Postman

Para facilitar a exploração e teste da API, utilize o [projeto do postman](anexos/SP%20TRANS.postman_collection.json) disponível na pasta **anexos** deste repositório.

### Acesso básico

* Entre na sessão de desenvolvedor [Meus Aplicativos](http://www.sptrans.com.br/desenvolvedores/perfil-desenvolvedor/meus-aplicativos/) e adicione um novo aplicativo
* Copie a "Chave" do aplicativo desejado

![Chave](imagens/chave_api_exemplo.png)

* Cole a "Chave" na variável do projeto `token`

![Variaveis do postman](imagens/postman_variaveis.png)

* Envie a requisição `POST` chamada de `Autenticação` no projeto, se for retornado `true` o acesso a API foi concedido para chave inserida e já é possível enviar as outras requisições.

## 🚀 Considerações
- O tempo de resposta da API pode variar devido à atualização dos dados em tempo real.
- Certifique-se de armazenar sua chave de acesso com segurança.
- Caso tenha problemas com a API, verifique a documentação oficial da SPTrans ou tente utilizar o proxy da Aiko.