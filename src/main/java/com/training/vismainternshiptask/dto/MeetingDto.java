package com.training.vismainternshiptask.dto;

import com.training.vismainternshiptask.enums.Category;
import com.training.vismainternshiptask.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDto {

  private Long id;

  @NotBlank(message = "A meeting requires a name!")
  @Size(min = 3, message = "Responsible should contain at least 3 characters!")
  private String name;

  @NotBlank(message = "No responsible person given!")
  private String responsiblePerson;

  @NotBlank(message = "No description!")
  @Size(min = 10, message = "description should contain at least 10 characters!")
  private String description;

  @NotNull(message = "No category")
  private Category category;

  @NotNull(message = "No type")
  private Type type;

  @NotBlank(message = "No start date")
  private String startDate;

  @NotBlank(message = "No end date")
  private String endDate;
}
