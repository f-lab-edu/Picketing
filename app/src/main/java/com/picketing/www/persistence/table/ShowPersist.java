package com.picketing.www.persistence.table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShowPersist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Genre genre;
	private SubGenre subGenre;
	private LocalDate startDate;
	private LocalDate endDate;
	private String venue;
	private Long runningTime;
	private Long intermission;

	private AgeGroup ageGroup;
	private String details;
	private boolean isBookable;
	private Long ownerId;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	@OneToMany(mappedBy = "show")
	private List<ShowSeatGradePersist> seatGrades;

	public ShowPersist createShowPersist(Long id) {
		this.id = id;
		return this;
	}
}
