package thd.gameobjects.unmovable;

class ScoreBlockImages {
    static String generateZero(char colorCode) {
        String flag = """
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX
                XXX       XXXXXXX                   
                XXXXXXXXXXXXXXXXX                        
                XXXXXXXXXXXXXXXXX                        
                """;
        return flag.replace('X', colorCode);
    }

    static String generateOne(char colorCode) {
        String flag = """
                          XXXXXXX
                         XXXXXXXX
                        XXXXXXXXX
                       XXXX XXXXX
                      XXXX  XXXXX
                     XXXX   XXXXX
                    XXXX    XXXXX
                   XXXX     XXXXX
                  XXXX      XXXXX
                 XXXX       XXXXX
                XXXX        XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                      """;
        return flag.replace('X', colorCode);
    }

    static String generateTwo(char colorCode) {
        String flag = """
                  XXXXXXXXXXXXXX
                 XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXX        XXXXXX
                XX         XXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXX
                         XXXXX
                        XXXX   
                       XXXX
                      XXX
                    XXX
                  XXX
                 XXX                            
                XXXXXXXXXXXXXXXXX
                 XXXXXXXXXXXXXXXX
                  XXXXXXXXXXXXXXX
                """;
        return flag.replace('X', colorCode);
    }

    static String generateThree(char colorCode) {
        String flag = """
                XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXXX
                    XXXXXXXXXXXXX
                    XXXXXXXXXXXXX
                    XXXXXXXXXXXXX  
                           XXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXXX
                XXXXXXXXXXXXXXXXX                            
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXX
                """;
        return flag.replace('X', colorCode);
    }

    static String generateFour(char colorCode) {
        String flag = """
                          XXXXXXX
                         XXXXXXXX
                        XXXXXXXXX
                       XXX  XXXXX
                      XXX   XXXXX
                     XXX    XXXXX
                    XXX     XXXXX
                   XXX      XXXXX
                  XXX       XXXXX
                 XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                            XXXXX
                """;
        return flag.replace('X', colorCode);
    }

    static String generateFive(char colorCode) {
        String flag = """
                 XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXX
                XXXXXX
                XXXXXX
                XXXXXX
                XXXXXX
                XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                 XXXXXXXXXXXXXXXX   
                           XXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXXX
                           XXXXXX
                XXXXXXXXXXXXXXXXX                            
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXX
                """;
        return flag.replace('X', colorCode);
    }

    static String generateSix(char colorCode) {
        String flag = """
                 XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXX
                XXXXXX
                XXXXXX
                XXXXXX
                XXXXXX
                XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX   
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXXXXXXXXXXXXX                            
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXX
                """;
        return flag.replace('X', colorCode);
    }

    static String generateSeven(char colorCode) {
        String flag = """
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                             XXXX
                             XXXX
                            XXXXX
                            XXXX
                           XXXX
                         XXXX
                        XXXX
                       XXXX
                      XXXX
                     XXXX
                    XXXX
                   XXXX
                  XXXX
                 XXXX
                XXXX                   
                XXXX
                """;
        return flag.replace('X', colorCode);
    }

    static String generateEight(char colorCode) {
        String flag = """
                 XXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                 XXXXX       XXX
                   XXXXXXXXXXX
                   XXXXXXXXXXX
                 XXXXX       XXX   
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXXXXXXXXXXXXX                            
                 XXXXXXXXXXXXXXXX
                  XXXXXXXXXXXXXX
                """;
        return flag.replace('X', colorCode);
    }

    static String generateNine(char colorCode) {
        String flag = """
                 XXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXX        XXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXXX   
                              XXX
                              XXX
                              XXX
                              XXX
                              XXX
                XXXXXXXXXXXXXXXXX                            
                XXXXXXXXXXXXXXXXX
                XXXXXXXXXXXXXXXX
                """;
        return flag.replace('X', colorCode);
    }
}
