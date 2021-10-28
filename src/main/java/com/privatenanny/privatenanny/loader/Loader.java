package com.privatenanny.privatenanny.loader;

import com.privatenanny.privatenanny.model.Group;
import com.privatenanny.privatenanny.model.Task;
import com.privatenanny.privatenanny.model.Utilisateur;
import com.privatenanny.privatenanny.repository.GroupRepository;
import com.privatenanny.privatenanny.repository.TaskRepository;
import com.privatenanny.privatenanny.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Loader.
 */
@Configuration
public class Loader {
    /**
     * Instantiates a new Loader.
     *
     * @throws ParseException the parse exception
     */
    public Loader() throws ParseException{
    }

    /**
     * Clr command line runner.
     *
     * @param utilisateurRepository the utilisateur repository
     * @param groupRepository       the group repository
     * @param taskRepository        the task repository
     * @return the command line runner
     */
    @Bean
    CommandLineRunner clr(UtilisateurRepository utilisateurRepository, GroupRepository groupRepository, TaskRepository taskRepository){
        return args -> {

            //création utilisateurs
            Utilisateur u1 = new Utilisateur("damien.cantin35@gmail.com", "DamDam", "Damien","+33684190317", "c1RZSU2x6tVSGzWQdlzDlFx9BI72");
            Utilisateur u2 = new Utilisateur("menanpatrik49@gmail.com", "Papatrik", "Patrik","+33766192071", "yGzjTi4WxoOATAwwNiqJ7n7915w2");
            Utilisateur u3 = new Utilisateur("damien.salerno35@gmail.com", "Sarrazin", "Damien","+33652147135", "cTvKeQDEIFOJ6TIqs6HYgt1Sb3C2");
            Utilisateur u4 = new Utilisateur("yvesdagri31@gmail.com", "ydagri", "Yves","+33684190319", "Vcq5X4s4kHQQqHF8OaxhqjsJ4yw1");
            Utilisateur u5 = new Utilisateur("Madoudagri31@gmail.com", "Mdagri", "Madou","+33684190320", "5Qw5iFOkIKdfvrHzyVxVS4mq9jo2");
            Utilisateur u6 = new Utilisateur("Aremdagri31@gmail.com", "Adagri", "Arem","+33684190352", "iNrUUXm7JEcvLolQTpDgZVBp1QU2");

            // AJout des contacts aux utilisateurs
            u1.getContacts().addAll(List.of(u2,u3,u4,u5,u6));
            u2.getContacts().addAll(List.of(u1,u3,u4,u5,u6));
            u3.getContacts().addAll(List.of(u2,u1,u4,u5,u6));
            u4.getContacts().addAll(List.of(u2,u3,u1,u5,u6));
            u5.getContacts().addAll(List.of(u2,u3,u4,u1,u6));
            u6.getContacts().addAll(List.of(u2,u3,u4,u5,u1));

            //création groupe pour u1
            Group g1 = new Group("Amis", u1);
            Group g2 = new Group("Enfants", u1);
            Group g3 = new Group("Jeunes", u1);
            Group g4 = new Group("Fac", u1);


            //ajout contact aux groupes de l'utilisateur u1
            g1.getGroupMembers().addAll(List.of(u2,u3,u4,u5,u6));
            g2.getGroupMembers().addAll(List.of(u2,u3,u4,u5,u6));
            g3.getGroupMembers().addAll(List.of(u2,u3,u4,u5,u6));
            g4.getGroupMembers().addAll(List.of(u2,u3,u4,u5,u6));

            //création groupe pour u2
            Group g5 = new Group("Amis", u2);
            Group g6 = new Group("Enfants", u2);
            Group g7 = new Group("Jeunes", u2);
            Group g8 = new Group("Fac", u2);

            //ajout contact aux groupes de l'utilisateur u2
            g5.getGroupMembers().addAll(List.of(u1,u3,u4,u5,u6));
            g6.getGroupMembers().addAll(List.of(u1,u3,u4,u5,u6));
            g7.getGroupMembers().addAll(List.of(u1,u3,u4,u5,u6));
            g8.getGroupMembers().addAll(List.of(u1,u3,u4,u5,u6));

            //création groupe utilisateur u3
            Group g9= new Group("Amis", u3);
            Group g10= new Group("Enfants", u3);
            Group g11= new Group("Jeunes", u3);
            Group g12= new Group("Fac", u3);



            //ajout contact aux groupes de l'utilisateur u3
            g9.getGroupMembers().addAll(List.of(u2,u1,u4,u5,u6));
            g10.getGroupMembers().addAll(List.of(u2,u1,u4,u5,u6));
            g11.getGroupMembers().addAll(List.of(u2,u1,u4,u5,u6));
            g12.getGroupMembers().addAll(List.of(u2,u1,u4,u5,u6));

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = dateFormatter.parse("2021-06-25 07:00:00");  //2021-06-25 07:00:00
            Date date2 = dateFormatter.parse("2021-06-25 10:00:00");  //2021-06-25 10:00:00
            Date date3 = dateFormatter.parse("2021-06-25 10:30:00");  //2021-06-25 10:30:00
            Date date4 = dateFormatter.parse("2021-06-30 18:30:00");  //2021-06-30 18:30:00
            Date date5 = dateFormatter.parse("2021-06-30 08:30:00");  //2021-06-30 08:30:00
            Date date6 = dateFormatter.parse("2021-06-29 08:30:00");  //2021-06-29 08:30:00
            Date date7 = dateFormatter.parse("2021-06-28 10:30:00");  //2021-06-28 10:30:00
            Date date8 = dateFormatter.parse("2021-06-27 18:30:00");  //2021-06-27 18:30:00
            Date date9 = dateFormatter.parse("2021-06-26 10:30:00");  //2021-06-26 10:30:00
            Date date10 =dateFormatter.parse("2021-06-26 10:00:00");  //2021-06-26 10:00:00

            List<Utilisateur> receiversU1 = new ArrayList() {{ add(u2); add(u3); }};
            List<Utilisateur> receiversU2 = new ArrayList() {{ add(u1); add(u2); add(u3); }};
            List<Utilisateur> receiversU3 = new ArrayList() {{ add(u3); }};
            List<Utilisateur> receiversU4 = new ArrayList() {{ add(u3); add(u4); add(u5); }};
            List<Utilisateur> receiversU5 = new ArrayList() {{ add(u5); }};
            List<Utilisateur> receiversU6 = new ArrayList() {{ add(u4); add(u5); add(u6); }};


            Task t1 = new Task(u1, "examen de prise de sang au medecin", "Visite medecin", receiversU1,
                    Task.Action.NOTIFICATION, true, 3, 30000L, date1);

            Task t2 = new Task(u1, "passer l'aspirateur avant que je sois rentre du travail", "Menage", receiversU1,
                    Task.Action.NOTIFICATION, true, 3, 30000L, date2);

            Task t3 = new Task(u2, "rappeler à mes amis et moi que l'on prend un verre ensemble", "Verre amis", receiversU2,
                    Task.Action.NOTIFICATION, true, 3, 30000L, date3);

            Task t4 = new Task(u3, "RDV restau ce soir", "Resto", receiversU3,
                    Task.Action.NOTIFICATION, true, 3, 30000L, date4);

            Task t5 = new Task(u4, "RDV shopping centre ALMA Rennes", "Shopping", receiversU4,
                    Task.Action.NOTIFICATION, true, 3,30000L, date5);

            Task t6 = new Task(u5, "faire à manger pour les enfants", "Cuisiner", receiversU5,
                    Task.Action.NOTIFICATION, true, 3,30000L, date6);

            Task t7 = new Task(u5, "aller à la banque", "RDV Banque", receiversU5,
                    Task.Action.NOTIFICATION, true, 3, 30000L, date7);

            Task t8 = new Task(u6, "rdv avec amis cinema", "Soiree Cinema", receiversU6,
                    Task.Action.NOTIFICATION, true, 3, 30000L, date8);

            Task t9 = new Task(u2, "reunion avec Virginie Sans", "Daily privateNanny", receiversU2,
                    Task.Action.NOTIFICATION, true, 3,30000L, date9);

            Task t10 = new Task(u3, "rappel aller faire les courses", "Courses", receiversU3,
                    Task.Action.NOTIFICATION, true, 3, 30000L, date10);


            utilisateurRepository.saveAll(List.of(u1,u2,u3,u4,u5,u6));
            groupRepository.saveAll(List.of(g1,g2,g3,g4,g5,g6,g7,g8,g9,g10,g11,g12));
            taskRepository.saveAll(List.of(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10));

            u1.getGroups().add(g1);
            u1.getGroups().add(g2);
            u1.getGroups().add(g3);
            u1.getGroups().add(g4);

            u2.getGroups().add(g5);
            u2.getGroups().add(g6);
            u2.getGroups().add(g7);
            u2.getGroups().add(g8);

            u3.getGroups().add(g9);
            u3.getGroups().add(g10);
            u3.getGroups().add(g11);
            u3.getGroups().add(g12);

            utilisateurRepository.saveAll(List.of(u1,u2,u3,u4,u5,u6));
        };
    }
}
