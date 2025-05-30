package gr.aueb.cf.mobilecontacts.model;

public abstract class AbstractEntity {
    private Long id;

    public AbstractEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
