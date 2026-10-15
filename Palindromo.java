/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.palindromos;

import java.util.Scanner;

/**
 * Práctica: Palíndromo recursivo en Java (patrón wrapper)
 * Materia: Estructura de Datos
 * Alumno: Kevyn Raúl Hernández Gallegos - Grado 4, Grupo C
 *
 * Un palíndromo es una palabra o frase que se lee igual
 * de izquierda a derecha que de derecha a izquierda.
 *
 * El programa tiene un menú: puedes escribir tu propia palabra
 * (y ver el paso a paso de la recursión) o correr las pruebas.
 */
public class Palindromo {

    // Si es true, el método auxiliar imprime cada llamada recursiva
    private boolean mostrarPasos = false;

    // Cuenta cuántas llamadas recursivas se han hecho (para la traza)
    private int llamada = 0;

    // ---------------------------------------------------------
    // WRAPPER (público): solo recibe la palabra, sin pasos
    // ---------------------------------------------------------
    public boolean esPalindromo(String palabra) {
        return evaluar(palabra, false);
    }

    // WRAPPER (público): igual que el anterior, pero muestra el paso a paso
    public boolean esPalindromoConPasos(String palabra) {
        return evaluar(palabra, true);
    }

    // ---------------------------------------------------------
    // Lógica del wrapper: valida, normaliza e inicia la recursión
    // ---------------------------------------------------------
    private boolean evaluar(String palabra, boolean pasos) {
        this.mostrarPasos = pasos;
        this.llamada = 0;

        // Validación: si es null no hay nada que evaluar
        if (palabra == null) {
            return false;
        }

        // Normalización: minúsculas y sin espacios
        // "Anita lava la tina" -> "anitalavalatina"
        String palabraLimpia = palabra.toLowerCase().replace(" ", "");

        if (mostrarPasos) {
            System.out.println("Texto original : \"" + palabra + "\"");
            System.out.println("Texto limpio   : \"" + palabraLimpia + "\"");
            System.out.println("Longitud       : " + palabraLimpia.length());
            System.out.println();
        }

        // Cadena vacía (o solo espacios): se considera palíndromo
        if (palabraLimpia.isEmpty()) {
            if (mostrarPasos) {
                System.out.println("La cadena está vacía, se considera palíndromo.");
            }
            return true;
        }

        // Iniciamos con inicio = 0 y fin = última posición
        return esPalindromoAuxiliar(palabraLimpia, 0, palabraLimpia.length() - 1);
    }

    // ---------------------------------------------------------
    // AUXILIAR RECURSIVO (privado)
    // inicio: avanza hacia la derecha (+1)
    // fin:    retrocede hacia la izquierda (-1)
    // ---------------------------------------------------------
    private boolean esPalindromoAuxiliar(String palabra, int inicio, int fin) {
        llamada++;
        if (mostrarPasos) {
            System.out.println("Llamada " + llamada + ": inicio = " + inicio + ", fin = " + fin);
        }

        // Caso base 1: los índices se igualaron o se cruzaron -> es palíndromo
        if (inicio >= fin) {
            if (mostrarPasos) {
                System.out.println("   -> Caso base 1: " + inicio + " >= " + fin + ", devuelve true");
            }
            return true;
        }

        char izquierda = palabra.charAt(inicio);
        char derecha = palabra.charAt(fin);

        // Caso base 2: las letras de los extremos son distintas -> NO es palíndromo
        if (izquierda != derecha) {
            if (mostrarPasos) {
                System.out.println("   -> Compara '" + izquierda + "' con '" + derecha
                        + "': NO coinciden. Caso base 2, devuelve false");
            }
            return false;
        }

        if (mostrarPasos) {
            System.out.println("   -> Compara '" + izquierda + "' con '" + derecha
                    + "': coinciden. Sigue con inicio + 1 y fin - 1");
        }

        // Paso recursivo: nos acercamos al centro
        return esPalindromoAuxiliar(palabra, inicio + 1, fin - 1);
    }

    // ---------------------------------------------------------
    // PRUEBAS
    // ---------------------------------------------------------
    private static void probar(Palindromo p, String texto, boolean esperado) {
        boolean resultado = p.esPalindromo(texto);
        String estado = (resultado == esperado) ? "OK   " : "ERROR";
        System.out.println("[" + estado + "] \"" + texto + "\" -> " + resultado
                + " (esperado: " + esperado + ")");
    }

    private static void ejecutarPruebas(Palindromo p) {
        System.out.println("\n=== Palabras palíndromas ===");
        probar(p, "reconocer", true);
        probar(p, "radar", true);
        probar(p, "somos", true);   // longitud impar
        probar(p, "abba", true);    // longitud par

        System.out.println("\n=== Palabras NO palíndromas ===");
        probar(p, "hola", false);
        probar(p, "java", false);
        probar(p, "recursion", false);

        System.out.println("\n=== Mayúsculas y espacios ===");
        probar(p, "Radar", true);
        probar(p, "Anita lava la tina", true);

        System.out.println("\n=== Casos borde ===");
        probar(p, "", true);        // cadena vacía
        probar(p, "a", true);       // un solo carácter
        probar(p, null, false);     // entrada nula
    }

    // ---------------------------------------------------------
    // MAIN: menú interactivo
    // ---------------------------------------------------------
    public static void main(String[] args) {
        Palindromo p = new Palindromo();
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n===== PALÍNDROMO RECURSIVO =====");
            System.out.println("1. Escribir una palabra o frase");
            System.out.println("2. Ejecutar pruebas automáticas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            String entrada = sc.nextLine().trim();
            if (entrada.equals("1")) {
                opcion = 1;
            } else if (entrada.equals("2")) {
                opcion = 2;
            } else if (entrada.equals("0")) {
                opcion = 0;
            } else {
                opcion = -1;
            }

            if (opcion == 1) {
                System.out.print("Escribe la palabra o frase: ");
                String texto = sc.nextLine();
                System.out.println("\n--- Paso a paso ---");
                boolean resultado = p.esPalindromoConPasos(texto);
                System.out.println("\nResultado: \"" + texto + "\" "
                        + (resultado ? "SÍ es un palíndromo." : "NO es un palíndromo."));
            } else if (opcion == 2) {
                ejecutarPruebas(p);
            } else if (opcion == -1) {
                System.out.println("Opción no válida, intenta de nuevo.");
            }
        }

        System.out.println("¡Hasta luego!");
        sc.close();
    }
}
