/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.palindromos;

    /**
 * Práctica: Palíndromo recursivo en Java (patrón wrapper)
 * Materia: Estructura de Datos
 * Alumno: Kevyn Raúl Hernández Gallegos - Grado 4, Grupo C
 *
 * Un palíndromo es una palabra o frase que se lee igual
 * de izquierda a derecha que de derecha a izquierda.
 */
public class Palindromos {
 
    // MÉTODO WRAPPER (público)
    // El usuario solo manda la palabra. Aquí se valida, se
    // normaliza el texto y se inicia la recursión con los índices.
    
    public boolean esPalindromo(String palabra) {
        // Validación: si es null no hay nada que evaluar
        if (palabra == null) {
            return false;
        }
 
        // Normalización: todo a minúsculas y sin espacios
        // "Anita lava la tina" -> "anitalavalatina"
        String palabraLimpia = palabra.toLowerCase().replace(" ", "");
 
        // Cadena vacía (o solo espacios): se considera palíndromo
        if (palabraLimpia.isEmpty()) {
            return true;
        }
 
        // Iniciamos con inicio = 0 (primera letra) y fin = última posición
        return esPalindromoAuxiliar(palabraLimpia, 0, palabraLimpia.length() - 1);
    }
 
    // MÉTODO AUXILIAR RECURSIVO (privado)
    // inicio: índice que avanza hacia la derecha (+1)
    // fin:    índice que retrocede hacia la izquierda (-1)
    private boolean esPalindromoAuxiliar(String palabra, int inicio, int fin) {
 
        // Caso base 1: los índices se igualaron o se cruzaron.
        // Ya se revisaron todas las letras y ninguna falló -> es palíndromo.
        if (inicio >= fin) {
            return true;
        }
 
        // Caso base 2: las letras de los extremos son distintas
        // -> NO es palíndromo, no hace falta seguir revisando.
        if (palabra.charAt(inicio) != palabra.charAt(fin)) {
            return false;
        }
 
        // Paso recursivo: las letras coinciden, nos acercamos al centro.
        return esPalindromoAuxiliar(palabra, inicio + 1, fin - 1);
    }
 
    // MÉTODO DE APOYO PARA IMPRIMIR LAS PRUEBAS
    private static void probar(Palindromos p, String texto, boolean esperado) {
        boolean resultado = p.esPalindromo(texto);
        String estado = (resultado == esperado) ? "OK   " : "ERROR";
        System.out.println("[" + estado + "] \"" + texto + "\" -> " + resultado
                + " (esperado: " + esperado + ")");
    }
 
    // MAIN: casos de prueba

    public static void main(String[] args) {
        Palindromos p = new Palindromos();
 
        System.out.println("=== Palabras palíndromas ===");
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
}
