import entities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        User user1 = User.builder().login("user1").password("1234567").biography("java dev").build();
        User user2 = User.builder().login("user2").password("1234567").biography("python dev").build();
        User user3 = User.builder().login("user3").password("1234567").biography("c# dev").build();
        User user4 = User.builder().login("admin").password("admin").biography("im admin").build();
        User user5 = User.builder().login("pro_gamer").password("asdfg").biography("Im pro gamer").build();
        saveOrUpdate(user1);
        saveOrUpdate(user2);
        saveOrUpdate(user3);
        saveOrUpdate(user4);
        saveOrUpdate(user5);

        Post post1 = Post.builder().text("Работа в офисе").user(user1).build();
        Post post2 = Post.builder().text("Вышел в отпуск").user(user1).build();
        Post post3 = Post.builder().text("Играю в теннис").user(user2).build();
        Post post4 = Post.builder().text("Приболел").user(user2).build();
        Post post5 = Post.builder().text("Я на ИК").user(user3).build();
        Post post6 = Post.builder().text("Купил новую машину").user(user3).build();
        Post post7 = Post.builder().text("Буду тех работы 15.05.2021 19:00").user(user4).build();
        Post post8 = Post.builder().text("Тех работы завершены").user(user4).build();
        Post post9 = Post.builder().text("Взял топ1").user(user5).build();
        Post post10 = Post.builder().text("Выйграл турнир").user(user5).build();



        saveOrUpdate(post1);
        saveOrUpdate(post2);
        saveOrUpdate(post3);
        saveOrUpdate(post4);
        saveOrUpdate(post5);
        saveOrUpdate(post6);
        saveOrUpdate(post7);
        saveOrUpdate(post8);
        saveOrUpdate(post9);
        saveOrUpdate(post10);

        Like like1 = Like.builder().user(user1).post(post5).build();
        Like like2 = Like.builder().user(user2).post(post5).build();
        Like like3 = Like.builder().user(user3).post(post2).build();
        Like like4 = Like.builder().user(user4).post(post1).build();
        Like like5 = Like.builder().user(user5).post(post9).build();
        saveOrUpdate(like1);
        saveOrUpdate(like2);
        saveOrUpdate(like3);
        saveOrUpdate(like4);
        saveOrUpdate(like5);

        Comment comment1 = Comment.builder().user(user1).post(post5).text("Классная машина!").build();
        Comment comment2 = Comment.builder().user(user2).post(post5).text("Понравился розовый цвет машины *_*").build();
        Comment comment3 = Comment.builder().user(user3).post(post2).text("Хорошо играешь!").build();
        saveOrUpdate(comment1);
        saveOrUpdate(comment2);
        saveOrUpdate(comment3);

        Follower follower1 = Follower.builder().userFollower(user1).userFollowed(user3).build();
        Follower follower2 = Follower.builder().userFollower(user3).userFollowed(user1).build();
        Follower follower3 = Follower.builder().userFollower(user3).userFollowed(user2).build();
        saveOrUpdate(follower1);
        saveOrUpdate(follower2);
        saveOrUpdate(follower3);

        user1.setPassword("qwerty");
        saveOrUpdate(user1);
    }

    public static<T> T saveOrUpdate(T entity){
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(entity);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Запись создана успешно");
        return entity;
    }


}
