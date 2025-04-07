package com.kocakdev.athletiq.service;

import java.util.List;

import com.kocakdev.athletiq.dto.TrainingDataCreateDTO;
import com.kocakdev.athletiq.model.Athlete;
import com.kocakdev.athletiq.model.TrainingData;
import com.kocakdev.athletiq.model.TrainingSession;

public interface TrainingSessionService {

public TrainingData createTrainingData(TrainingDataCreateDTO dto);

public TrainingSession createTrainingSession(List<TrainingData> trainingDataList, Athlete athlete);

}
