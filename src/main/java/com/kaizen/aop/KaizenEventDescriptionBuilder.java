package com.kaizen.aop;

import java.time.LocalDate;

public final class KaizenEventDescriptionBuilder {
    private int id;
    private LocalDate kaizenFillingDate;
    private boolean kaizenCompleted;
    private LocalDate kaizenCompletionDate;
    private String kaizenProblem;
    private String kaizenSolution;
    private boolean kaizenIsRewarded;


    private KaizenEventDescriptionBuilder(int id, LocalDate kaizenFillingDate, boolean kaizenCompleted, LocalDate kaizenCompletionDate,
                                          String kaizenProblem, String kaizenSolution, boolean kaizenIsRewarded) {
        this.id = id;
        this.kaizenFillingDate = kaizenFillingDate;
        this.kaizenCompleted = kaizenCompleted;
        this.kaizenCompletionDate = kaizenCompletionDate;
        this.kaizenProblem = kaizenProblem;
        this.kaizenSolution = kaizenSolution;
        this.kaizenIsRewarded = kaizenIsRewarded;

    }

    public static class Builder {

        private int id;
        private LocalDate kaizenFillingDate;
        private boolean kaizenCompleted;
        private LocalDate kaizenCompletionDate;
        private String kaizenProblem;
        private String kaizenSolution;
        private boolean kaizenIsRewarded;

        public KaizenEventDescriptionBuilder.Builder eventId (int id) {
            this.id = id;
            return this;
        }

        public KaizenEventDescriptionBuilder.Builder kaizenFillingDate(LocalDate kaizenFillingDate) {
            this.kaizenFillingDate = kaizenFillingDate;
            return this;
        }

        public KaizenEventDescriptionBuilder.Builder kaizenCompleted(boolean kaizenCompleted) {
            this.kaizenCompleted = kaizenCompleted;
            return this;
        }

        public KaizenEventDescriptionBuilder.Builder kaizenCompletionDate(LocalDate kaizenCompletionDate) {
            this.kaizenCompletionDate = kaizenCompletionDate;
            return this;
        }

        public KaizenEventDescriptionBuilder.Builder kaizenProblem(String kaizenProblem) {
            this.kaizenProblem = kaizenProblem;
            return this;
        }

        public KaizenEventDescriptionBuilder.Builder kaizenSolution(String kaizenSolution) {
            this.kaizenSolution = kaizenSolution;
            return this;
        }

        public KaizenEventDescriptionBuilder.Builder kaizenIsRewarded(boolean kaizenIsRewarded) {
            this.kaizenIsRewarded = kaizenIsRewarded;
            return this;
        }


        public KaizenEventDescriptionBuilder build() {
            return new KaizenEventDescriptionBuilder(id,kaizenFillingDate,kaizenCompleted,kaizenCompletionDate,
                    kaizenProblem,kaizenSolution,kaizenIsRewarded);
        }
    }

    @Override
    public String toString() {
        return id +
                ", =" + kaizenFillingDate +
                ", =" + kaizenCompleted +
                ", =" + kaizenCompletionDate +
                ", ='" + kaizenProblem + '\'' +
                ", ='" + kaizenSolution + '\'' +
                ", =" + kaizenIsRewarded;


    }
}
