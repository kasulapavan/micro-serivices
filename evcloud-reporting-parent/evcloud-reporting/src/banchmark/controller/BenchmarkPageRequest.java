package banchmark.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Month;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BenchmarkPageRequest {
    @NotNull
    private Integer year;
    @Min(1)
    @Max(4)
    private Integer quarter;
    private Month month;
}