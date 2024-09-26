public class Universidade {
    private String nomealuno;  // Nome do aluno
    private String raaluno;    // Registro Acadêmico do aluno
    private double[] notasaluno;   // Array para armazenar as notas
    private boolean ead;      // Indica se o curso é EAD (Educação a Distância)
    private double presencaaluno;  // Percentual de presença para disciplinas presenciais

    // CONSTRUTOR PARA CURSOS EAD - ONLINE
    public Universidade(String nome, String ra, double[] notas) {
        if (notas == null || notas.length == 0) throw new IllegalArgumentException("Notas não podem ser nulas ou vazias.");
        this.nomealuno = nome;      
        this.raaluno = ra;          
        this.notasaluno = notas;    
        this.ead = true;       
    }

    // CONSTRUTOR PARA CURSOS PRESENCIAIS
    public Universidade(String nome, String ra, double[] notas, double presenca) {
        this(nome, ra, notas);    
        if (presenca < 0 || presenca > 100) throw new IllegalArgumentException("Presença deve estar entre 0 e 100.");
        this.ead = false;         
        this.presencaaluno = presenca; 
    }

    // MÉTODO PARA CALCULAR A NOTA FINAL
    public double calcularNotaFinal() {
        double notaFinal = 0;                
        int totalAvaliacoes = notasaluno.length;  

        switch (totalAvaliacoes) {
            case 1:
            case 2:
                for (double nota : notasaluno) {
                    notaFinal += nota;             
                }
                return notaFinal / totalAvaliacoes; 

            case 3:
                return (notasaluno[0] + 2 * notasaluno[1] + 2 * notasaluno[2]) / 5; 

            case 4:
                return (notasaluno[0] * 0.15) + (notasaluno[1] * 0.30) + (notasaluno[2] * 0.10) + (notasaluno[3] * 0.45);

            default:
                throw new IllegalArgumentException("Número de avaliações inválido: " + totalAvaliacoes);
        }
    }

    // MÉTODO PARA VERIFICAR SE O ALUNO ESTÁ APROVADO
    public String verificarSit() {
        double notaFinal = calcularNotaFinal(); 
        if (ead) {
            return notaFinal >= 5 ? "Aprovado" : "Reprovado"; 
        } else {
            return (notaFinal >= 5 && presencaaluno >= 75) ? "Aprovado" : "Reprovado"; 
        }
    }

    // MÉTODO PARA IMPRIMIR AS INFORMAÇÕES DO ALUNO
    public void informacao() {
        double notaFinal = calcularNotaFinal(); 
        String situacao = verificarSit();   

        System.out.printf("Nome: %s\nRA: %s\nNota Final: %.2f\nSituação: %s\n", 
                          nomealuno, raaluno, notaFinal, situacao);
    }
    
    public static void main(String[] args) {
        double[] notasPresenciais = {9.0, 7.5, 5.0, 9.0};  
        Universidade aluno1 = new Universidade("Murillo Araujo", "248757", notasPresenciais, 90);
        aluno1.informacao();

        System.out.println();

        double[] notasEAD = {10.0, 7.0, 9.0};  
        Universidade aluno2 = new Universidade("Ana Patricia", "246543", notasEAD);
        aluno2.informacao();

        System.out.println();

        double[] notas2 = {4.0, 10.0};  
        Universidade aluno3 = new Universidade("Carlos Augusto", "268721", notas2, 80);
        aluno3.informacao();
    }
}
