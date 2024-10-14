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

      User user1 = new User("Igor", "Loginov", "log@gmail.com");
      user1.setCar(new Car("Ford Focus", 5));
      userService.add(user1);

      User user2 = new User("Georgi", "Lagidze", "dagi@gmail.com");
      user2.setCar(new Car("Buick", 45));
      userService.add(user2);

      User user3 = new User("Konstantin", "Kiselev", "kis@gmail.com");
      user3.setCar(new Car("Mazda", 3));
      userService.add(user3);

      System.out.println(userService.findUserByCar("Ford Focus", 5));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
