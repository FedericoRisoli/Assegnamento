## HOW TO USE TESTS
### Aggiungere test:
Per il test della classe Esempio creiamo un file TestEsempio.java nella cartella src/test/java/[package], successivamente implementiamo i test mettendo sopra ogni metodo @Test, esattamente come in TestData.java (i nomi son stati mixati per sperimentare i limiti del sistema quindi trovi test a volte prima a volte dopo)
### Eseguire test:
Quando si pusha su master o si fa una merge request su master Github lo farà in automatico. Se vicino al commit trovi una spunta verde allora tutti i test sono passati, un pallino giallo indica test in corso, mentre una X rossa indica Fallimento del test (o build, bisogna guardare il codice specifico nella tab Actions).
In caso di X su un commit su master fixare immediatamente. Se è su un ramo diverso si può aspettare per il fix

## HOW TO USE SERVER
1. Run ConcurrentServer.java
2. Per mandare un messaggio da un controllore qualunque basta usare sendMessage(String message).
Esempio
````
sendMessage("sono un messaggio da inviare");
````
3. Inserire nel controller che vuole Ricevere messaggi dal server le seguenti righe in initialize():
````
createTask(server.getSocket(),this);
````
4. Ora i messaggi vengono stampati in console, per utilizzarli diversamente aggiungere la funzione:
````
@Override
    public void handleMessage(String message) {
        //cancellare la linea qui sotto e metter il metodo vero
        System.out.println("OVERRIDE RIUSCITO! FUNGE! IL MESSAGGIO ERA"+message);
    }
````
**NB Modificare handleMessage per leggere il messaggio e fare le azioni necessarie**

**NB: senza override non funzionerà.**

5. Per cambiare scena completamente dal controllore (non per usare per i popup quindi) usare il metodo
   MyController.changeScene(String NomeScenaFXML, ActionEvent event)

Esempio:

````
changeScene(pagocarta.fxml, event);
````

**ATTENZIONE: Le maiuscole e minuscole devono essere come scritte qui. event è la variabile che c'è nelle funzioni che vengono chiamate quando premi un pulsante ad esempio**


### Altro:
Per passare da una scena all'altra usare se si è usato il server nel controllore:
MyController.changeScene(String NomeScenaFXML, ActionEvent event)
````
changeScene(personnelpage.fxml, event);
````


# WineShop Assegnametno cose da fare:
https://www.notion.so/WineShop-59d2574effbe4c10973fc829cbd06632

## Testo Originale:
L’obiettivo è sviluppare (usando il linguaggio Java) e documentare un sistema software per la vendita
online di bottiglie di vino, utilizzando in modo appropriato le tecniche di riferimento della
programmazione orientata agli oggetti. Il sistema realizzato sarà installato presso l’azienda che ha
finanziato la realizzazione del sistema.

Il sistema interagisce con clienti (persone che vogliono acquistare del vino), impiegati (persone che
gestiscono la vendita e la spedizione e ricezione dei prodotti) e un amministratore (persona che
controlla il lavoro dei dipendenti e gestisce le attività finanziarie dell’azienda).

I clienti sono identificati da: nome, cognome, codice fiscale, indirizzo email, numero telefonico e indirizzo di
consegna. Gli impiegati e l’amministratore sono identificati da: nome, cognome, codice fiscale,
indirizzo email, numero telefonico e indirizzo di residenza. Per le consegne dei vini ai clienti e per la
fornitura dei vini, l’azienda si serve dei servizi di un corriere e di un fornitore. Queste due persone
sono identificate da: nome, cognome, indirizzo email, numero telefonico, e codice fiscale e indirizzo
della loro azienda.
I vini, sono identificati dai seguenti attributi: nome, produttore, provenienza, anno, note tecniche, e i
vitigni da cui derivano. Ogni bottiglia ha un prezzo che dipende dalla qualità del vino e dal suo numero
di vendite.

Per poter utilizzare il sistema, un nuovo cliente deve registrarsi sul sistema inserendo nome utente e
password e le altre informazioni richieste dal sistema. Un cliente registrato può fare ricerca dei vini
per nome e anno di produzione e acquistare bottiglie di vino dopo un accesso autenticato. Ogni volta
che un cliente accede nel sistema, il sistema informa il cliente sulle promozioni in corso. Anche gli
impiegati e l’amministratore devono accedere al sistema tramite nome utente e password. e possono
modificare la loro password. In aggiunta, l’amministratore ha una password iniziale predefinita che
può comunque modificare. Inoltre l’amministratore deve registrare (con nome utente e password) gli
altri dipendenti, e resettare e cancellare i loro dati di registrazione quando diventa necessario.
Gli impiegati e l’amministratore possono fare la ricerca dei clienti per cognome, dei vini per nome
e/o anno di produzione, degli ordini di vendita e di acquisto e delle proposte di acquisto per intervallo
di date. Inoltre, l’amministratore deve preparare un report mensile sullo stato dell’azienda (introiti,
spese, numero bottiglie vendute e disponibili alla vendita, numero di vendite per i diversi vini,
valutazione dei dipendenti).

Un cliente può acquistare le bottiglie di vino con confezioni da 1 a 5 bottiglie e da casse da 6 e 12
bottiglie. Ognuna di queste casse contiene solo bottiglie dello stesso tipo di vino.
La vendita di casse di vino di 6 e 12 bottiglie è valorizzata da uno sconto del 5 e 10%. La vendita di
più di una cassa è valorizzata da un ulteriore sconto del 2 e 3%.
Il sistema deve tenere traccia per ogni tipo di vino del numero di bottiglie vendute e di quelle ancora
disponibili per la vendita.

Un cliente può visualizzare i vini in vendita e selezionare uno o più vini e, per ogni vino, selezionare
il numero di bottiglie (default 1). Selezionate le bottiglie, il cliente può decidere di acquistarle. Il
sistema permette questa operazione fornendo la possibilità di pagare tramite un bonifico o una carta
di credito. Fatto il pagamento, il cliente riceve un ordine di vendita contenente le informazioni su: il
cliente che ha fatto l’acquisto, i vini acquistati (nome vino, numero bottiglie e prezzo), l’indirizzo di
consegna e la data di consegna.

Quando non c’è un numero sufficiente di bottiglie di uno o più vini per soddisfare la richiesta del
cliente del numero di bottiglie che vorrebbe acquistare: quando le bottiglie richieste saranno
disponibili, il sistema notificherà la loro disponibilità all’utente.

Nel caso in cui il numero di bottiglie di uno o più vini non riesce a soddisfare la richiesta del cliente,
allora il cliente può compilare una proposta di acquisto contenente: i vini che vuole acquistare (nome
vino, numero bottiglie) e l’indirizzo di consegna. Ricevuta la proposta di acquisto, il sistema invia
una copia dell’ordine di vendita ad uno degli impiegati. Questo impiegato preparerà un ordine di
acquisto e lo invia al fornitore. Ricevuto l’ordine, il fornito prepara il materiale ed effettua la
consegna. Ricevuto il materiale dal fornitore, l’impiegato chiede al sistema di generare un ordine di
vendita partendo dai dati della proposta di acquisto, dai prezzi dei vini e dalla data di consegna
(definita con il corriere) e invia l’ordine di vendita. Ricevuto l’ordine di vendita, il cliente può
decidere se acquistarlo o no. Se si, il cliente deve pagare tramite il sistema; se no, il cliente deve
annullare l’acquisto. Il sistema e il dipendente che ha seguito la possibile vendita devono completare
le attività necessarie in base alla scelta fatta dal cliente.

Il sistema mantiene e gestisce gli ordini di vendita ricevuti dai clienti. In particolare, ogni volta che
viene effettuato un acquisto, il sistema aggiorna il numero di vendita e disponibilità dei vini, e invia
una copia dell’ordine di vendita ad uno degli impiegati. Questo impiegato deve esaminare l’ordine,
preparare il materiale per la spedizione, concordare con il corriere la data di prelievo e consegna del
materiale e informare il cliente sulla data di consegna. Dopo la consegna del materiale al corriere, il
dipendete deve completare l’ordine di vendita firmandolo digitalmente.

Il sistema deve garantire che per ogni tipo di vino ci sia sempre un numero ragionevole di bottiglie.
Per gestire questo vincolo, ad ogni arrivo di un ordine di acquisto il sistema controlla se il numero di
bottiglie di alcuni vini è sceso sotto la soglia (il valore della soglia può dipendere dal vino e dai suoi
numeri di vendita). Se ciò avviene, il sistema informa uno dei dipendenti sulle quantità disponibili di
questi vini. Ricevuta l’informazione, l’impiegato preparerà un ordine di acquisto e lo invia al
fornitore. Ricevuto l’ordine, il fornito prepara il materiale ed effettua la consegna. Ricevuto il
materiale dal fornitore, l’impiegato deve completare l’ordine di acquisto firmandolo digitalmente, e
caricare l’ordine sul sistema. Il sistema elabora l’ordine e aggiorna le quantità di vini.

Il sistema è basato su un’architettura client server. Il server deve supportare l’accesso in parallelo di
diversi nodi client. L’interfaccia grafica dei client deve essere implementata con JavaFX. Il database
del sistema deve mantenere le informazioni di almeno una decina di vini differenti.

Il numero di impiegati al lavoro deve essere almeno tre e il sistema assegna le attività agli impiegati
con una politica “round-robin” ovviamente tenendo conto degli impiegati al momento impegnati. Se
l’attività non è terminata entro un tempo prestabilito, il sistema l’assegna ad un altro impiegato. Il
sistema mantiene le informazioni sulle attività completate e non completate dai dipendenti. 
