import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The {@code Utilities} class provides utility methods for common operations.
 * It contains static methods and cannot be instantiated.
 *
 * @author Iuliia Mozhina
 * @author Alejandra Camelo Cruz
 * @version 1.0
 */
public final class Utilities {
  /** The logger of the Utilities class. */
  private static final Logger logger = Logger.getLogger(Utilities.class.getName());

  /** Private constructor to prevent instantiation. */
  private Utilities() {}

  /**
   * Reads words from an Excel file and returns a list of Word objects.
   *
   * @param inputStream The path to the Excel file.
   * @return A list of Word objects.
   */
  public static List<Word> readWordsFromExcel(final InputStream inputStream) {
    final List<Word> words = new ArrayList<>();

    try (Workbook workbook = new XSSFWorkbook(inputStream)) {
      final Sheet sheet = workbook.getSheetAt(0);

      for (final Row row : sheet) {
        final Iterator<Cell> cellIterator = row.cellIterator();

        if (cellIterator.hasNext()) {
          final Cell originalCell = cellIterator.next();
          final String original = originalCell.getStringCellValue();

          if (cellIterator.hasNext()) {
            final Cell translationCell = cellIterator.next();
            final String translation = translationCell.getStringCellValue();

            words.add(new Word(original, translation));
          }
        }
      }
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error reading words from Excel file", e);
    }

    return words;
  }}
