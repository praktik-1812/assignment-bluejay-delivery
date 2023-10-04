import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;


public class employeeAnalyze {
    public static void main(String[] args) {
        List<employeeRecord> records = new ArrayList<>();

        // Read and parse the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("Assignment_Timecard.xlsx - Sheet1.csv"))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                records.add(new employeeRecord(data));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // Implement conditions
        //set is used  to eliminate the duplicate records in the output
        Set<String> consecutiveDaysEmployees = new HashSet<>();
        Set<String> lessThan10HoursEmployees = new HashSet<>();
        Set<String> moreThan14HoursEmployees = new HashSet<>();
        employeeRecord prevRecord = null;

        // Sort records by employee name and date
        // sort function is used to arrange the records in the chronological order and will be easier to find output
        records.sort(Comparator.nullsFirst(
                Comparator.comparing(employeeRecord::getEmployeeName)
                        .thenComparing(employeeRecord::getTimeIn, Comparator.nullsFirst(Comparator.naturalOrder()))
        ));

        try (PrintWriter writer = new PrintWriter("output.txt"))
        {
            for (employeeRecord record : records) {
                if (prevRecord != null && prevRecord.getEmployeeName().equals(record.getEmployeeName())) {
                    long diffInMillis = record.getTimeIn() != null && prevRecord.getTimeOut() != null ?
                            record.getTimeIn().getTime() - prevRecord.getTimeOut().getTime() : 0;
                    int diffInHours = (int) (diffInMillis / (60 * 60 * 1000));

                    // Condition a: Worked for 7 consecutive days
                    consecutiveDaysEmployees.add(record.getEmployeeName());

                    // Condition b: Less than 10 hours between shifts but greater than 1 hour
                    if (diffInHours < 10 && diffInHours > 1) {
                        lessThan10HoursEmployees.add(record.getEmployeeName());
                    }
                } else {
                    consecutiveDaysEmployees.clear();
                }

                // Condition c: Worked for more than 14 hours in a single shift
                if (record.getTimecardHours() > 840) { // 14 hours * 60 minutes
                    moreThan14HoursEmployees.add(record.getEmployeeName());
                }

                prevRecord = record;
            }

            writer.println("Employees who worked for 7 consecutive days:");
            consecutiveDaysEmployees.forEach(name -> writer.println(name));

            writer.println("\n");

            writer.println("Employees with less than 10 hours between shifts but greater than 1 hour:");
            lessThan10HoursEmployees.forEach(name -> writer.println(name));
            writer.println("\n");

            writer.println("Employees who worked for more than 14 hours in a single shift:");
            moreThan14HoursEmployees.forEach(name -> writer.println(name));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
