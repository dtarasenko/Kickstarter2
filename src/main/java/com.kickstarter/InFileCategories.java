package com.kickstarter;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DTarasenko on 2/21/2016.
 */
public class InFileCategories implements Categories {


    private final File file;

    public InFileCategories(String fileName) {
        this.file = createFileIfNeeded(fileName);
    }

    @Override
    public void add(Category category) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file, true));
            out.append(category.getName() + "\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден", e);
        } catch (IOException e) {
            throw  new RuntimeException("Что-то не так с чтением из файла");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException("Не могли закрыть файл", e);
                }
            }
        }
    }

    @Override //TODO to use Collection
    public String[] getCategories() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            List<String> result = new LinkedList<>();
            String line = in.readLine();
            int index = 1;
            while (line != null) {
                result.add(index + " - " + line);
                line = in.readLine();
                index++;
            }

            return result.toArray(new String[0]);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден", e);
        } catch (IOException e) {
            throw  new RuntimeException("Что-то не так с чтением из файла");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("Не могли закрыть файл", e);
                }
            }
        }
    }

    @Override
    public Category get(int index) {
        BufferedReader in = null; // TODO подумать над выделением
        try {
            in = new BufferedReader(new FileReader(file));

            String line = in.readLine();
            int current = 0;
            while (line != null) {
                if (current == index) {
                    break;
                }
                line = in.readLine();
                current++;
            }

            return new Category(line);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден", e);
        } catch (IOException e) {
            throw  new RuntimeException("Что-то не так с чтением из файла");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("Не могли закрыть файл", e);
                }
            }
        }
    }

    @Override
    public int size() {
        BufferedReader in = null; // TODO подумать над выделением
        try {
            in = new BufferedReader(new FileReader(file));

            int counter = 0;
            String line = in.readLine();

            while (line != null) {
                counter++;
                line = in.readLine();
            }

            return counter;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден", e);
        } catch (IOException e) {
            throw  new RuntimeException("Что-то не так с чтением из файла");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("Не могли закрыть файл", e);
                }
            }
        }
    }

    private File createFileIfNeeded(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Не возможно создать файл контейнер", e);
            }
        }
        return file;
    }
}
