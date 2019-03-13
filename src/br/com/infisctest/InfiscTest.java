package br.com.infisctest;

import br.com.infictest.service.ClientAgeAndDayBirth;
import br.com.infictest.service.EmployeeTime;
import br.com.infictest.service.EmployeeTimeContract;
import br.com.infictest.service.EmployeeTotalSale;
import br.com.infictest.service.TargetFile;
import br.com.infictest.service.ClientTotalSale;
import br.com.infisctest.model.Sale;
import br.com.infisctest.model.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Paulo Rodrigues
 */
public class InfiscTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoSuchFieldException {
        
        System.out.print("Digite o endereço do arquivo: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        //"/home/paulo/dados.txt"
        HashMap fileContent = new HashMap();
        fileContent = TargetFile.readInformation(filePath);
        
        ArrayList employees = (ArrayList) fileContent.get("employees");
        ArrayList clients = (ArrayList) fileContent.get("clients");
        ArrayList sales = (ArrayList) fileContent.get("sales");

        System.out.println("a) Quantidade total de funcionários: ");
        System.out.println(employees.size());
        System.out.println("");

        System.out.println("b) Quantidade total de clientes: ");
        System.out.println(clients.size());
        System.out.println("");

        System.out.println("c) Quantidade total de vendas: ");
        System.out.println(sales.size());
        System.out.println("");

        System.out.println("d) Valor total de vendas: ");
        Double saleTotalValue = TargetFile.sumSalesValue(sales);
        System.out.println(TargetFile.formatDoubleToReais(saleTotalValue));
        System.out.println("");

        System.out.println("e) Valor de venda por mês: ");
        Map<Integer, List<Sale>> saleGroupedByYear = TargetFile.groupingSaleByYear(sales);
        Map<Integer, List<Sale>> saleGroupedByMonth = TargetFile.groupingSaleByMonth(sales);
        for (Map.Entry<Integer, List<Sale>> pairMonthAndSales : saleGroupedByMonth.entrySet()) {
            System.out.println("Mês: " + TargetFile.getNameMonth(pairMonthAndSales.getKey()));
            Double salesMonthValue = TargetFile.sumSalesValue((ArrayList) pairMonthAndSales.getValue());
            System.out.println("Valor: " + TargetFile.formatDoubleToReais(salesMonthValue));
        }
        System.out.println("");

        System.out.println("f) Funcionário destaque por mês: ");
        for (Map.Entry<Integer, List<Sale>> pairMonthAndSales : saleGroupedByMonth.entrySet()) {
            System.out.println("Mês: " + TargetFile.getNameMonth(pairMonthAndSales.getKey()));
            Map<Integer, List<Sale>> saleGroupedByEmployeeOfMonth = TargetFile.groupingSaleByEmployee((ArrayList) pairMonthAndSales.getValue());
            EmployeeTotalSale bestEmployee = TargetFile.getBestEmployeeByMonth(saleGroupedByEmployeeOfMonth, employees);
            System.out.println("ID: " + bestEmployee.getId() + "; Nome: " + bestEmployee.getName());
        }
        System.out.println("");

        System.out.println("g) Clientes aniversariantes de cada mês: ");
        Map<Integer, List<Client>> clientsGroupedByMonth = TargetFile.groupingClientsByMonth(clients);
        for (Map.Entry<Integer, List<Client>> pairMonthAndClients : clientsGroupedByMonth.entrySet()) {
            System.out.println("Mês: " + TargetFile.getNameMonth(pairMonthAndClients.getKey()));

            List<ClientAgeAndDayBirth> clientsAgeAndDayBirth = TargetFile.getClientsAgeAndDayBirth((ArrayList) pairMonthAndClients.getValue());
            for (ClientAgeAndDayBirth cliBirthDay : clientsAgeAndDayBirth) {
                System.out.println("Nome: " + cliBirthDay.getName() + "; Idade: " + cliBirthDay.getAge() + " anos; Dia do aniversário: " + cliBirthDay.getDayBirth());
            }
        }
        System.out.println("");

        System.out.println("h) Total de valor de vendas por cliente: ");
        Map<Integer, List<Sale>> salesGroupedByClient = TargetFile.groupingSalesByClient(sales);
        for (Map.Entry<Integer, List<Sale>> pairClientAndSales : salesGroupedByClient.entrySet()) {
            System.out.println("Cliente: " + TargetFile.getClientName(pairClientAndSales.getKey(), clients));
            Double salesClientValue = TargetFile.sumSalesValue((ArrayList) pairClientAndSales.getValue());
            System.out.println("Valor: " + TargetFile.formatDoubleToReais(salesClientValue));
        }
        System.out.println("");

        System.out.println("i) Total de valor de vendas por funcionário: ");
        Map<Integer, List<Sale>> salesGroupedByEmployee = TargetFile.groupingSaleByEmployee(sales);
        for (Map.Entry<Integer, List<Sale>> pairEmployeeAndSales : salesGroupedByEmployee.entrySet()) {
            System.out.println("Funcionário: " + TargetFile.getEmployeeName(pairEmployeeAndSales.getKey(), employees));
            Double salesEmployeeValue = TargetFile.sumSalesValue((ArrayList) pairEmployeeAndSales.getValue());
            System.out.println("Valor: " + TargetFile.formatDoubleToReais(salesEmployeeValue));
        }
        System.out.println("");

        System.out.println("j) Cliente que mais comprou na história da empresa: ");
        ClientTotalSale bestClient = TargetFile.getBestClient(salesGroupedByClient, clients);
        System.out.println("Nome: " + bestClient.getName());
        System.out.println("");

        System.out.println("k) Os três funcionários com mais tempo de trabalho na empresa: ");
        List<EmployeeTime> employeesTime = TargetFile.getEmployeeTimeContractSorted(employees);
        for (int i = 0; i < 3; i++) {
                System.out.println("Nome: " + employeesTime.get(i).getName() + "; Data contratação: " + employeesTime.get(i).getDay() + "/" + employeesTime.get(i).getMonth() + "/" + employeesTime.get(i).getYear() );
            }
        System.out.println("");

        System.out.println("l) Lista funcionários que mais venderam: ");
        List<EmployeeTotalSale> salesGroupedByEmployeeSorted = TargetFile.getEmployeesBySaleSortedName(salesGroupedByEmployee, employees);
            for (EmployeeTotalSale empSales : salesGroupedByEmployeeSorted) {
                System.out.println("Nome: " + empSales.getName() + "; Valor: R$ "+ TargetFile.formatDoubleToReais(empSales.getValue()));
            }
        System.out.println("");

        System.out.println("m) Lista da quantidade de tempo de funcionários: ");
        List<EmployeeTimeContract> employeesTimeContract = TargetFile.getEmployeeTimeContract(employees);
        for (EmployeeTimeContract empTime : employeesTimeContract) {
                System.out.println(empTime.getYearQuantity() + " anos, " + empTime.getMonthQuantity() + " meses, " + empTime.getDayQuantity() + " dias");
            }
    }

}
