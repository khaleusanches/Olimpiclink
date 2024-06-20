
import com.example.olimpiclink.models.User

class DAO {
    public var users = mutableListOf<User>()
    fun registerUserDatabase(user : User){
        users.add(user)
    }
    fun lerUserDatabase() : String {
        return users[0].nome;
    }
}
