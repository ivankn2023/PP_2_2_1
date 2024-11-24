package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 =new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(new Car(123, "BMW"));
      userService.add(user1);


      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(new Car(1234, "Audi"));
      userService.add(user2);


      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user2.setCar(new Car(12345, "Mercedes"));
      userService.add(user3);

      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Cars = "+user.getCar());
         System.out.println();
      }



      User finduser = userService.getUserByCarSettings("Audi", 1234);
      System.out.println("-----------");
      System.out.println(finduser);
      System.out.println("-----------");
      context.close();
   }
}
