package org.skhuton.fitpete.team.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long teamId;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @Builder
    public Team(String teamName, List<Member> members) {
        this.teamName = teamName;
        this.members = members != null ? members : new ArrayList<>();
    }

    public void addMember(Member member) {
        this.members.add(member);
        if (member.getTeam() != this) {
            member.setTeam(this);
        }
    }

    public void removeMember(Member member) {
        this.members.remove(member);
        if (member.getTeam() == this) {
            member.setTeam(null);
        }
    }

    public List<Member> getMembersSortedByLevel() {
        return members.stream()
                .sorted(Comparator.comparingInt(Member::getLevelCount).reversed())
                .collect(Collectors.toList());
    }
}
