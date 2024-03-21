/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectop1_miguelmoran;

import java.util.Scanner;

/**
 *
 * @author flash
 */
public class ProyectoP1_MiguelMoran {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean seguir = true;
        while (seguir) {
            String[][] tablero1 = new String[10][10];
            String[][] tablero2 = new String[10][10];
            tablero1 = Llenado(tablero1);
            tablero2 = Llenado(tablero2);
            System.out.println("Jugador 1. Posicione sus Barcos");
            tablero1 = Barcos(tablero1);
            System.out.println("Jugador 2. Posicione sus Barcos");
            tablero2 = Barcos(tablero2);
            String[][] void1 = new String[10][10];
            String[][] void2 = new String[10][10];
            void1 = Llenado(void1);
            void2 = Llenado(void2);
            int damageP1 = 0;
            int damageP2 = 0;
            int x = 0;
            int y = 0;
            while (damageP1 < 30 && damageP2 < 30) {
                boolean seguir1 = true;
                while (seguir1) {
                    System.out.print("Ingrese las coordenadas del ataque (Jugador 1): ");
                    String coordenadas = sc.next();
                    int x1 = Integer.parseInt(String.valueOf(coordenadas.charAt(0)));
                    int y1 = Integer.parseInt(String.valueOf(coordenadas.charAt(1)));
                    x = x1 - 1;
                    y = y1 - 1;
                    if (x >= 0 && x < 9 && y >= 0 && y < 9) {
                        seguir1 = false;
                    } else {
                        System.out.println("Las coodenadas ingresadas no son validas");
                    }
                }
                damageP2 = Ataque(tablero2, void2, x, y, damageP2);
                Impresion(void2);
                if (damageP2 < 30) {
                    boolean seguir2 = true;
                    while (seguir2) {
                        System.out.print("Ingrese las coordenadas del ataque (Jugador 2): ");
                        String coordenadas = sc.next();
                        int x1 = Integer.parseInt(String.valueOf(coordenadas.charAt(0)));
                        int y1 = Integer.parseInt(String.valueOf(coordenadas.charAt(1)));
                        x = x1 - 1;
                        y = y1 - 1;
                        if (x >= 0 && x < 9 && y >= 0 && y < 9) {
                            seguir2 = false;
                        } else {
                            System.out.println("Las coodenadas ingresadas no son validas");
                        }
                    }
                    damageP1 = Ataque(tablero1, void1, x, y, damageP1);
                    Impresion(void1);
                }
            }
            if (damageP2 == 30) {
                System.out.println("El Jugador 1 ha ganado. Felicidades!");
            }
            if (damageP1 == 30) {
                System.out.println("El Jugador 2 ha ganado. Felicidades!");
            }
            System.out.println("Tablero del Jugador 1:");
            Impresion(tablero1);
            System.out.println("Tablero del Jugador 2:");
            Impresion(tablero2);
        }
    }

    public static String[][] Llenado(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = "   ";
            }
        }
        return matriz;
    }

    public static void Impresion(String[][] matriz) {
        System.out.println("-----------------------------------------");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("|");
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].equals(" X ")) {
                    System.out.print("\u001B[31m" + matriz[i][j]);
                    System.out.print("\u001B[37m|");
                } else {
                    System.out.print(matriz[i][j] + "|");
                }
            }
            System.out.println("");
            System.out.println("-----------------------------------------");
        }
    }

    public static String[][] Barcos(String[][] tablero) {
        int[] barcos = new int[10];
        for (int i = 0; i < barcos.length; i++) {
            if (i < 4) {
                barcos[i] = 2;
            } else if (i < 7) {
                barcos[i] = 3;
            } else if (i < 10) {
                barcos[i] = 4;
            } else {
                barcos[i] = 5;
            }
        }
        int barco = 0;
        for (int i = 0; i < barcos.length; i++) {
            Impresion(tablero);
            while (barco < 1 || barco > 11 || barcos[barco - 1] == 0) {
                for (int j = 0; j < barcos.length; j++) {
                    switch (barcos[j]) {
                        case 2 -> {
                            System.out.println((j + 1) + ". Patrulla");
                        }
                        case 3 -> {
                            System.out.println((j + 1) + ". Submarino");
                        }
                        case 4 -> {
                            System.out.println((j + 1) + ". Transportador");
                        }
                        case 5 -> {
                            System.out.println((j + 1) + ". Battleship");
                        }
                    }
                }
                System.out.print("Elija el barco que desea posicionar: ");
                barco = sc.nextInt();
                if (barco < 1 || barco > 11) {
                    System.out.println("Elija un barco valido.");
                } else if (barcos[barco - 1] == 0) {
                    System.out.println("Ese barco ya fue posicionado");
                }
            }
            int posicionx = 0;
            int posiciony = 0;
            while (posicionx < 0 || posicionx > 9 || posiciony < 0 || posiciony > 9) {
                System.out.println("Elija las coordenadas a donde ira la punta del barco: ");
                System.out.print("X:");
                posicionx = sc.nextInt();
                System.out.print("Y: ");
                posiciony = sc.nextInt();
                if (posicionx < 0 || posicionx > 9 || posiciony < 0 || posiciony > 9) {
                    System.out.println("Elija una posicion valida.");
                }
            }
            int opcion = 0;
            boolean vertical = false;
            while (opcion != 1 && opcion != 2) {
                System.out.println("Desea posicionar el barco vertical o horizontalmente: ");
                System.out.println("1. Vertical");
                System.out.println("2. Horizontal");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1 -> {
                        vertical = true;
                    }
                    case 2 -> {
                        vertical = false;
                    }
                    default -> {
                        System.out.println("Elija una opcion valida.");
                    }
                }
            }
            if (vertical) {
                if (barcos[barco] > posiciony) {
                    for (int j = posiciony; j < barcos[barco - 1] + posiciony; j++) {
                        tablero[posicionx][i] = " * ";
                    }
                } else if (barcos[barco] + posiciony > 9) {
                    for (int j = posiciony; j < posiciony - barcos[barco - 1]; j--) {
                        tablero[posicionx][i] = " * ";
                    }
                }
            } else {
                if (barcos[barco - 1] > posicionx) {
                    for (int j = posicionx; j < barcos[barco - 1] + posicionx ; j++) {
                        tablero[i][posiciony] = " * ";
                    }
                } else if (barcos[barco - 1] + posicionx > 9) {
                    for (int j = posicionx; j < posicionx - barcos[barco - 1]; j--) {
                        tablero[i][posiciony] = " * ";
                    }
                }
            }
            barcos[barco - 1] = 0;
        }
        return tablero;
    }

    public static int Ataque(String[][] tablero, String[][] tabvoid, int x, int y, int damage) {
        if (tablero[y][x].equals(" * ")) {
            tabvoid[y][x] = " X ";
            tablero[y][x] = " X ";
            damage += 1;
        } else if (tablero[y][x].equals("   ")) {
            tabvoid[y][x] = " x ";
            tablero[y][x] = " x ";
        } else {
            System.out.println("Ese espacio ya fue atacado");
        }
        return damage;
    }
}
