//package br.com.oobj.escalar.jms;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Enfileirador {
//
//    @Value("${spring.active-mq.queue}")
//    private String queue;
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public Enfileirador(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//    public void enviaRequisicaoParaFila(String requisicao) {
//        try {
//            String[] requisicaoArray = requisicao.split("IMPRESSORA;MANIFESTO");
//            for (int i = 1; i < (requisicaoArray.length); i++) {
//                jmsTemplate.convertAndSend(queue, "{user: 'wolmir', usando: 'fila'}");
//            }
//        } catch (Exception e) {
//            System.out.println("Erro ao enviar o arquivo para fila!");
//            e.printStackTrace();
//        }
//    }
//}
