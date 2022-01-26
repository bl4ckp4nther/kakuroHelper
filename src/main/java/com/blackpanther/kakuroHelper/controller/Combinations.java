package com.blackpanther.kakuroHelper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Combinations {

    @GetMapping("/kakuroHelper/{target}/{places}")
    public List<ArrayList<Integer>> getCombinations(
            @PathVariable Integer target,
            @PathVariable Integer places){
        return calculateCombinations(new ArrayList<>(), 1, target, places, new ArrayList<>());
    }

    private List<ArrayList<Integer>> calculateCombinations(List<ArrayList<Integer>> result, Integer start, Integer num, Integer k, ArrayList<Integer> currRes){
        if(num==0 && k==0){
            result.add(new ArrayList<>(currRes));
            return result;
        }
        if(num<=0 || k==0 || start > 9 || start > num){
            return null;
        }
        for(int i=start; i<=9; i++){
            if(i>num){
                break;
            }
            currRes.add(i);
            calculateCombinations(result, i+1, num-i, k-1, currRes);
            currRes.remove(new Integer(i));
        }
        return result;
    }
}
