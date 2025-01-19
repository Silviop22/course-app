package al.sda.user;

import al.sda.course.Course;

import java.time.LocalDate;

public class Student extends User{
    private double credit;
    private LocalDate registerDate;

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
}
