import java.io.*;
        import java.util.*;

class Question {
    String question;
    String reponse;

    public Question(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    public String getQuestion() {
        return question;
    }

    public String getReponse() {
        return reponse;
    }
}

class Quiz {
    List<Question> questions;
    int score;
    Scanner scanner;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    public void jouer() {
        Map<String, Integer> scores = chargerScores(); // Charger les scores à l'intérieur de la méthode
        if (questions.size() < 10) {
            System.out.println("Il n'y a pas assez de questions pour jouer.");
            return;
        }

        System.out.println("Bienvenue dans le quiz sur les préfets des régions de Côte d'Ivoire !");
        System.out.print("Entrez votre pseudo : ");
        String pseudo = scanner.nextLine();

        Collections.shuffle(questions);

        for (int i = 0; i < 10; i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + q.getQuestion());
            System.out.print("Votre réponse : ");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase(q.getReponse())) {
                System.out.println("Correct !");
                score++;
            } else {
                System.out.println("Incorrect. La réponse correcte était : " + q.getReponse());
            }
        }

        System.out.println("Fin de la partie !");
        System.out.println("Votre score est de : " + score + "/10");

        scores.put(pseudo, score); // Ajouter le score du joueur
        sauvegarderScores(scores);

        afficherMeilleursScores(scores);
    }

    public void afficherMeilleursScores(Map<String, Integer> scores) {
        System.out.println("Meilleurs scores :");

        // Convertir la map des scores en une liste d'entrées
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());


        // Trier la liste des scores dans l'ordre décroissant des valeurs (scores)
        Collections.sort(list, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Afficher les trois meilleurs scores
        int size = Math.min(3, list.size()); // Nombre de scores à afficher

        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + list.get(i).getKey() + " : " + list.get(i).getValue() + "/10");
        }
    }




    private void sauvegarderScores(Map<String, Integer> scores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("scores.txt", true))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des scores : " + e.getMessage());
        }
    }


    public static Map<String, Integer> chargerScores() {
        Map<String, Integer> scores = new HashMap<>();
        File file = new File("scores.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Erreur lors de la création du fichier : " + e.getMessage());
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                scores.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des scores : " + e.getMessage());
        }
        System.out.println(scores);
        return scores;
    }

    public void chargerEtAfficherMeilleursScores() {
        Map<String, Integer> scores = chargerScores();
        afficherMeilleursScores(scores);
    }
}


public class Main {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Quel est le préfet de la région de Abidjan ?", "Vincent Toh Bi Irié"));
        questions.add(new Question("Quel est le préfet de la région du Bas-Sassandra ?", "Samy Merhy"));
        questions.add(new Question("Quel est le préfet de la région de la Comoé ?", "Christophe Tchao"));
        questions.add(new Question("Quel est le préfet de la région de l'Indénié-Djuablin ?", "Christine G. Dénis"));
        questions.add(new Question("Quel est le préfet de la région de la Marahoué ?", "Coulibaly Zié Omer"));
        questions.add(new Question("Quel est le préfet de la région de la Mé ?", "Djè Bi Djè Vangah"));
        questions.add(new Question("Quel est le préfet de la région de la Nawa ?", "Jean Claude Kwassi"));
        questions.add(new Question("Quel est le préfet de la région du Poro ?", "Aboua Dieudonné"));
        questions.add(new Question("Quel est le préfet de la région du Sud-Bandama ?", "Hermann Oulai"));
        questions.add(new Question("Quel est le préfet de la région du Worodougou ?", "Issa Coulibaly"));
        questions.add(new Question("Quel est le préfet de la région du Bafing ?", "Seydou Diakité"));
        questions.add(new Question("Quel est le préfet de la région du Bagoué ?", "Koné Wemain"));
        questions.add(new Question("Quel est le préfet de la région du Gbêkê ?", "Ouattara Karamoko"));
        questions.add(new Question("Quel est le préfet de la région du Gôh ?", "Kouakou Brigitte"));
        questions.add(new Question("Quel est le préfet de la région du Gontougo ?", "Kaba Nialé"));
        questions.add(new Question("Quel est le préfet de la région du Hambol ?", "Léon Kouakou Koffi"));
        questions.add(new Question("Quel est le préfet de la région du Iffou ?", "Valentin Kouassi"));
        questions.add(new Question("Quel est le préfet de la région du Kabadougou ?", "Jean François Loukou"));
        questions.add(new Question("Quel est le préfet de la région du Nzi ?", "Kouakou Ahoussou"));
        questions.add(new Question("Quel est le préfet de la région du Savanes ?", "Sanogo Zoumana"));
        questions.add(new Question("Quel est le préfet de la région du Sud-Comoé ?", "Zouzoua Armand"));
        questions.add(new Question("Quel est le préfet de la région du Tchologo ?", "Issa Coulibaly"));
        questions.add(new Question("Quel est le préfet de la région du Bounkani ?", "Konaté Lancina"));
        questions.add(new Question("Quel est le préfet de la région du Folon ?", "Kourouma Maman"));
        questions.add(new Question("Quel est le préfet de la région du Ganzourgou ?", "Coulibaly Souleymane"));
        questions.add(new Question("Quel est le préfet de la région du Haut-Sassandra ?", "Augustin Kouamé"));
        questions.add(new Question("Quel est le préfet de la région du Ioba ?", "Traoré N'wokou"));
        questions.add(new Question("Quel est le préfet de la région du Kénédougou ?", "Zoumbri Paul"));
        questions.add(new Question("Quel est le préfet de la région du Koulpélogo ?", "Diallo Boubacar"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Quiz quiz = new Quiz(questions);
            quiz.jouer();
            System.out.print("Voulez-vous jouer encore ? (non[pour arrêter]) : ");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("non")) {
                break;
            }
        }
    }
}
