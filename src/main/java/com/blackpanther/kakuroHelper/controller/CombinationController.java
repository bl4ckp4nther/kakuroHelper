package com.blackpanther.kakuroHelper.controller;

import com.blackpanther.kakuroHelper.dto.KakuroDto;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CombinationController {

    private static String getStringFromList(List<Integer> integerList) {
        return integerList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    @GetMapping
    public String home(){
        return "Welcome to kakuro helper!";
    }

    @GetMapping("/{target}/{places}")
    public KakuroDto getCombinations(
            @PathVariable Integer target,
            @PathVariable Integer places) {
        List<ArrayList<Integer>> combinations = calculateCombinations(new ArrayList<>(), 1, target, places, new ArrayList<>());
        if(CollectionUtils.isEmpty(combinations)){
            return KakuroDto.builder()
                    .target(target)
                    .digits(places)
                    .noOfResults(0).build();
        }

        List<String> strCombinations = combinations.stream().map(CombinationController::getStringFromList).collect(Collectors.toList());
        return KakuroDto.builder()
                .target(target)
                .digits(places)
                .combinations(strCombinations)
                .noOfResults(strCombinations.size()).build();
    }

    private List<ArrayList<Integer>> calculateCombinations(List<ArrayList<Integer>> result, Integer start, Integer num, Integer k, ArrayList<Integer> currRes) {
        if (num == 0 && k == 0) {
            result.add(new ArrayList<>(currRes));
            return result;
        }
        if (num <= 0 || k == 0 || start > 9 || start > num) {
            return null;
        }
        for (int i = start; i <= 9; i++) {
            if (i > num) {
                break;
            }
            currRes.add(i);
            calculateCombinations(result, i + 1, num - i, k - 1, currRes);
            currRes.remove(new Integer(i));
        }
        return result;
    }
}
