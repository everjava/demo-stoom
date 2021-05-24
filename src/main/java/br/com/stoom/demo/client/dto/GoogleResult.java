package br.com.stoom.demo.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GoogleResult {

    private List<Result> results;
}
