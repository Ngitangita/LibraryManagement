package org.example.Entity;

import java.util.Objects;

public class Topic {
    private int id;
    private Book book;
    private TopicName topicName;

    public Topic(){
        this.id = id;
        this.book = book;
        this.topicName = topicName;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getBook(){
        return book;
    }

    public void setBook(String book){
        this.book = book;
    }

    public String getTopicName(){
        return topicName;
    }

    public void setTopicName(TopicName topicName){
        this.topicName = topicName;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;
        Topic topic = (Topic) o;
        return id == topic.id && Objects.equals ( book, topic.book ) && topicName == topic.topicName;
    }

    @Override
    public int hashCode(){
        return Objects.hash ( id, book, topicName );
    }

    @Override
    public String toString(){
        return "Topic{" +
                "id=" + id +
                ", book=" + book +
                ", topicName=" + topicName +
                '}';
    }
}
