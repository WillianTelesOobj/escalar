# escalar
Avaliação Prática do Alpinista Willian Teles Pinto para o Projeto Escalar da empresa Oobj Tecnologia da Informação.

O projeto a ser desenvolvido consiste de uma solução de integração nos moldes das soluções conhecidas no mercado.
Há 5 componentes principais: Sender, Integrador, Enfileirador, Broker e Receiver.
Cada componente é descrito abaixo:

● A aplicação Sender, que poderá ser um ERP ou um PDV e, neste caso, você poderá
utilizar uma ferramenta como o Postman ou curl para simular as chamadas aos
endpoints da API de integração.

● O Integrador HTTP é um dos componentes a ser desenvolvido no projeto e que
será um responsável por expor os endpoints de integração e salvar o conteúdo
da requisição em um pasta de entrada da aplicação. Este componente também é
responsável por iniciar o fluxo apresentado acima que é executado
automaticamente em resposta à “chegada” de uma requisição.

● O Enfileirador que é outro componente a ser desenvolvido cujo papel é ler os
arquivos da pasta de entrada e enfileirar os dados no Message Broker.

● O Broker aqui desempenhado pelo Apache ActiveMQ irá distribuir as mensagens
para as múltiplas instâncias consumidoras da aplicação Receiver.

● O Receiver irá consumir as mensagens do Broker e, neste momento, apenas
simular através de saída no console a impressão dos manifestos de transporte.

A execução do fluxo apresentado acima deve ocorrer de forma automática em
resposta ao evento de “chegada” de uma requisição no Integrador HTTP.