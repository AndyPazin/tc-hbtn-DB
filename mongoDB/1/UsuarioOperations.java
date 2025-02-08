import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class UsuarioOperations {

    private MongoCollection<Document> usuarios;

    public UsuarioOperations(MongoDBConnection connection) {
        this.usuarios = connection.getDatabase().getCollection("usuarios"); // Nome da coleção: usuarios
    }

    public void inserirUsuarios() {
        Usuario alice = new Usuario("Alice", 25);
        Usuario bob = new Usuario("Bob", 30);
        Usuario charlie = new Usuario("Charlie", 35);

        usuarios.insertMany(java.util.Arrays.asList(alice.toDocument(), bob.toDocument(), charlie.toDocument()));
        System.out.println("Usuários inseridos.");
    }

    public void consultarUsuarios() {
        System.out.println("\nUsuários cadastrados:");
        for (Document doc : usuarios.find()) {
            System.out.println(Usuario.fromDocument(doc));
        }
    }

    public void atualizarUsuario(String nome, int idade) {
        usuarios.updateOne(Filters.eq("nome", nome), Updates.set("idade", idade));
        System.out.println("Idade de " + nome + " atualizada para " + idade + ".");
    }

    public void apagarUsuario(String nome) {
        usuarios.deleteOne(Filters.eq("nome", nome));
        System.out.println("Usuário " + nome + " apagado.");
    }

    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();
        UsuarioOperations operations = new UsuarioOperations(connection);

        operations.inserirUsuarios();
        operations.consultarUsuarios();

        operations.atualizarUsuario("Bob", 32);
        operations.consultarUsuarios();

        operations.apagarUsuario("Charlie");
        operations.consultarUsuarios();

        connection.closeConnection();
    }
}