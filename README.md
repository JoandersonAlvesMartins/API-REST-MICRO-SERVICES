Desenvolvendo um Microservices usando o Modelo de maturidade de Richardson para APIs REST 

Para padronizar e facilitar o desenvolvimento de APIs REST, Leonard Richardson propôs um modelo de maturidade para esse tipo de API, definido em 4 níveis.

Nível 0 — POX
Esse é considerado o nível mais básico e uma API que implementa apenas esse nível não pode ser considerada REST.
Nesse nível os nomes dos recursos não seguem qualquer padrão e estão sendo usados apenas para fazer invocação de métodos remotos. 
Nesse nível usamos o protocolo HTTP para comunicação, mas sem seguir qualquer tipo de regras para implementar os métodos.

Para facilitar o entendimento podemos observar no quadro abaixo a modelagem de uma API para um crud de clientes.


Nível 0
No modelo acima fazemos uso apenas dos métodos GET e POST e como já citado, os nomes dos métodos não seguem nenhum padrão.

Nível 1 — Recursos (Resources)
Nesse nível fazemos uso de recursos para modelar a API, para representar cada recurso fazemos uso de substantivos no plural.
No exemplo do crud de cliente, os recursos seriam identificados pelo substantivo “clientes”.

Um detalhe interessante é que no nível 1 já usamos os verbos HTTP de forma correta, já que se os verbos não fossem usados, as rotas de pesquisar, alterar e incluir ficariam ambíguas.


Nível 1
Nível 2 — Verbos HTTP
Como já foi adiantado no nível 1, o nível 2 se encarrega de garantir que os verbos HTTP sejam usados de forma correta. Os verbos mais utilizados são GET, POST, PUT e DELETE.


Nível 2
Os métodos GET, PUT e DELETE são considerados idempotente.
Um método é considerado idempotente quando uma requisição idêntica pode ser executada várias vezes sem alterar o estado do servidor.

Nível 3 — HATEOAS
O nível 3 é sem dúvidas o menos explorado, muitas APIs existentes no mercado não implementam esse nível.

HATEOAS significa Hypermedia as the Engine of Application State. 
Uma API que implementa esse nível fornece aos seus clientes links que indicarão como poderá ser feita a navegação entre seus recursos. 
Ou seja, quem for consumir a API precisará saber apenas a rota principal e a resposta dessa requisição terá todas as demais rotas possíveis.


Nível 3
No exemplo acima, podemos ver o resultado de uma API que implementa HATEAOS, veja que na resposta dessa API há uma coleção “links”, cada link aponta para uma rota dessa API. 
No caso desse exemplo, temos um link para a própria rota, um link para alterar um cliente e outra para exlcuir.
