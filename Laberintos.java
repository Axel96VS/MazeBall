package com.example.mazeball;

public class Laberintos {

    public static Bola getLaberinto(int eleccion) {

        Bola bola = null;

        if(eleccion == 1) {
            bola = new Bola();
            boolean[][] horizontales = new boolean[][]{
                    {true ,false,false,false,true ,false,false},
                    {true ,false,false,true ,false,true ,true },
                    {false,true ,false,false,true ,false,false},
                    {false,true ,true ,false,false,false,true },
                    {true ,false,false,false,true ,true ,false},
                    {false,true ,false,false,true ,false,false},
                    {false,true ,true ,true ,true ,true ,false},
                    {false,false,false,true ,false,false,false}
            };
            boolean[][] verticales = new boolean[][]{
                    {false,false,true ,true ,false,false,true ,false},
                    {false,false,true ,true ,false,true ,false,false},
                    {true ,true ,false,true ,true ,false,true ,true },
                    {false,false,true ,false,true ,true ,false,false},
                    {false,true ,true ,true ,true ,false,true ,true },
                    {true ,false,false,true ,false,false,true ,false},
                    {false,true ,false,false,false,true ,false,true }
            };
            bola.setLineasVerticales(horizontales);
            bola.setLineasHorizontales(verticales);
            bola.setPosicionInicial(0, 0);
            bola.setPosicionFinal(7, 7);
        }

        if(eleccion == 2) {
            bola = new Bola();
            boolean[][] verticales = new boolean[][]{
                    {false,false,false,true ,false,false,false},
                    {false,false,true ,false,true ,false,false},
                    {false,false,true ,true ,false,false,false},
                    {false,false,true ,true ,true ,false,false},
                    {false,false,true ,false,true ,false,false},
                    {true ,false,false,true ,false,true ,false},
                    {true ,false,true ,true ,false,false,false},
                    {false,false,true ,false,false,false,true }
            };
            boolean[][] horizontales = new boolean[][]{
                    {false,true ,true ,true ,false,true ,true ,true },
                    {true ,true ,false,false,true ,true ,true ,false},
                    {false,true ,true ,false,false,false,true ,true },
                    {true ,true ,false,false,false,true ,true ,false},
                    {false,true ,true ,true ,true ,false,true ,false},
                    {false,false,true ,false,false,true ,true ,true },
                    {false,true ,false,false,true ,true ,false,false}
            };
            bola.setLineasVerticales(verticales);
            bola.setLineasHorizontales(horizontales);
            bola.setPosicionInicial(0, 7);
            bola.setPosicionFinal(7, 0);
        }

        if(eleccion == 3) {
            bola = new Bola();
            boolean[][] verticales = new boolean[][]{
                    {false,false,true ,false,false,false,true ,false,false,false,false,false},
                    {false,true ,false,false,false,true ,false,false,false,false,true ,true },
                    {true ,false,false,false,false,true ,false,false,false,false,true ,true },
                    {true ,true ,false,false,false,true ,true ,true ,false,false,true ,true },
                    {true ,true ,true ,false,false,true ,true ,false,true ,false,true ,true },
                    {false,true ,true ,true ,false,true ,false,false,false,true ,false,false},
                    {false,false,false,true ,false,true ,false,true ,false,false,false,false},
                    {false,false,true ,false,true ,false,true ,true ,false,true ,false,false},
                    {true ,true ,true ,true ,false,true ,true ,false,false,true ,false,false},
                    {false,false,false,true ,false,false,true ,true ,false,true ,true ,false},
                    {false,false,true ,false,true ,false,true ,false,false,false,false,false},
                    {true ,true ,true ,true ,true ,true ,true ,false,false,true ,false,false},
                    {false,false,true ,false,false,true ,false,false,false,false,true ,false}
            };
            boolean[][] horizontales = new boolean[][]{
                    {true ,false,false,true ,true ,false,false,false,true ,true ,true ,true ,false},
                    {false,true ,true ,true ,true ,true ,true ,true ,true ,true ,false,false,false},
                    {false,false,true ,true ,true ,false,false,true ,true ,true ,true ,false,false},
                    {false,false,false,true ,true ,true ,false,false,false,true ,false,false,false},
                    {false,false,false,false,true ,false,false,true ,true ,true ,false,false,false},
                    {true ,true ,false,false,false,true ,true ,true ,true ,false,true ,true ,true },
                    {false,true ,true ,true ,true ,true ,false,false,false,true ,true ,true ,false},
                    {true ,false,false,false,true ,false,true ,false,true ,false,false,true ,true },
                    {false,true ,false,false,false,true ,false,true ,true ,true ,true ,true ,false},
                    {true ,true ,false,true ,false,true ,true ,false,false,true ,false,true ,false},
                    {false,true ,true ,false,true ,false,false,true ,true ,false,true ,true ,true },
                    {false,true ,false,false,true ,false,false,true ,true ,true ,false,false,true }

            };
            bola.setLineasVerticales(verticales);
            bola.setLineasHorizontales(horizontales);
            bola.setPosicionInicial(0, 0);
            bola.setPosicionFinal(12, 12);
        }
        return bola;
    }
}
