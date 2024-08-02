package org.skhuton.fitpete.information.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InformationStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INFORMATION_STORE_ID")
    private long informationStoreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INFORMATION_ID")
    private Information information;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public InformationStore(Information information, Member member) {
        this.information = information;
        this.member = member;
    }
}
