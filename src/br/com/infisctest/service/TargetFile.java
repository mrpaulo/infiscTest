package br.com.infisctest.service;

import br.com.infisctest.model.Sale;
import br.com.infisctest.model.Employee;
import br.com.infisctest.model.Client;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Paulo Rodrigues
 */
public class TargetFile {
    
    //Função para obter a informação que contém no arquivo
    public static HashMap readInformation(String path) throws FileNotFoundException, IOException {

        HashMap<String, ArrayList> returnList = new HashMap<String, ArrayList>();

        try {
            FileReader fileTarget = new FileReader(path);
            BufferedReader fileRead = new BufferedReader(fileTarget);
            ArrayList employeesList = new ArrayList<Employee>();
            ArrayList clientsList = new ArrayList<Client>();
            ArrayList salesList = new ArrayList<Sale>();
            String line = "";

            try {
                line = fileRead.readLine();
                while (line != null) {
                    String register[] = line.split("[|]");
                    if (Integer.parseInt(register[0]) == 0) {
                        String contractDate[] = register[3].split("-");
                        Employee employee = new Employee(register[1], register[2], contractDate);
                        employeesList.add(employee);

                    }

                    if (Integer.parseInt(register[0]) == 1) {
                        String birthDate[] = register[3].split("-");
                        Client client = new Client(register[1], register[2], birthDate);
                        clientsList.add(client);
                    }

                    if (Integer.parseInt(register[0]) == 2) {
                        String saleDate[] = register[5].split("-");
                        Sale sale = new Sale(register[1], register[2], register[3], register[4], saleDate);
                        salesList.add(sale);
                    }

                    line = fileRead.readLine();
                }
                fileRead.close();

                returnList.put("employees", employeesList);
                returnList.put("clients", clientsList);
                returnList.put("sales", salesList);

            } catch (IOException err) {
                throw new IOException();
            }
        } catch (FileNotFoundException err) {
            throw new FileNotFoundException();
        }

        return returnList;
    }
    
    public static String formatDoubleToReais(Double value) {

        String result = String.format("%.2f", value);

        return "R$ " + result;
    }

    //Função para a soma dos valores das vendas
    public static Double sumSalesValue(ArrayList<Sale> sales) {

        Double salesValueTotal = 0.0;
        for (int i = 0; i < sales.size(); i++) {
            Sale sale = sales.get(i);
            salesValueTotal += sale.getValue(); //sale.Value;
                    
        }

        return salesValueTotal;
    }

    //Função que transforma o numeral do mês em uma string do mês
    public static String getNameMonth(int month) {

        String result = "";

        if (month <= 0 || month >= 13) {
            result = "Mês inválido";

        } else {

            switch (month) {
                case 1:
                    result = "Janeiro";
                    break;
                case 2:
                    result = "Fevereiro";
                    break;
                case 3:
                    result = "Março";
                    break;
                case 4:
                    result = "Abril";
                    break;
                case 5:
                    result = "Maio";
                    break;
                case 6:
                    result = "Junho";
                    break;
                case 7:
                    result = "Julho";
                    break;
                case 8:
                    result = "Agosto";
                    break;
                case 9:
                    result = "Setembro";
                    break;
                case 10:
                    result = "Outubro";
                    break;
                case 11:
                    result = "Novembro";
                    break;
                case 12:
                    result = "Dezembro";
                    break;
            }
        }

        return result;
    }

    //Função que faz o agrupamento das vendas por mês
    public static Map<Integer, List<Sale>> groupingSaleByYear(ArrayList<Sale> sales) {

        Map<Integer, List<Sale>> saleGroupedByYear = new HashMap<Integer, List<Sale>>();
        for (Iterator it = sales.iterator(); it.hasNext();) {
            Sale iSale = (Sale) it.next();
            int key = iSale.getSaleDateYear();
            if (saleGroupedByYear.get(key) == null) {
                saleGroupedByYear.put(key, new ArrayList<Sale>());
            }
            saleGroupedByYear.get(key).add(iSale);
        }

        return saleGroupedByYear;
    }
    
    //Função que faz o agrupamento das vendas por mês
    public static Map<Integer, List<Sale>> groupingSaleByMonth(ArrayList<Sale> sales) {

        Map<Integer, List<Sale>> saleGroupedByMonth = new HashMap<Integer, List<Sale>>();
        for (Iterator it = sales.iterator(); it.hasNext();) {
            Sale iSale = (Sale) it.next();
            int key = iSale.getSaleDateMonth();
            if (saleGroupedByMonth.get(key) == null) {
                saleGroupedByMonth.put(key, new ArrayList<Sale>());
            }
            saleGroupedByMonth.get(key).add(iSale);
        }
        
        return saleGroupedByMonth;
    }

    //Função que faz o agrupamento das vendas por funcionário
    public static Map<Integer, List<Sale>> groupingSaleByEmployee(ArrayList<Sale> sales) {

        Map<Integer, List<Sale>> saleGroupedByEmployee = new HashMap<Integer, List<Sale>>();
        for (Iterator it = sales.iterator(); it.hasNext();) {
            Sale iSale = (Sale) it.next();
            int key = iSale.getEmployeeId();
            if (saleGroupedByEmployee.get(key) == null) {
                saleGroupedByEmployee.put(key, new ArrayList<Sale>());
            }
            saleGroupedByEmployee.get(key).add(iSale);
        }

        return saleGroupedByEmployee;
    }

    //Função que retorna o melhor funcionário por mês
    public static EmployeeTotalSale getBestEmployeeByMonth(Map<Integer, List<Sale>> saleGroupedByEmployeeOfMonth, ArrayList<Employee> employees) {

        ArrayList<EmployeeTotalSale> empTotalSaleList = new ArrayList<EmployeeTotalSale>();
        for (Map.Entry<Integer, List<Sale>> pairEmployeeIdAndSales : saleGroupedByEmployeeOfMonth.entrySet()) {
            int employeeId = pairEmployeeIdAndSales.getKey();//ID do funcionario
            
            Double totalSalesValueOfMonth = TargetFile.sumSalesValue((ArrayList) pairEmployeeIdAndSales.getValue()); //total vendas do mês

            String employeeName = TargetFile.getEmployeeName(employeeId, employees);
            
            EmployeeTotalSale empTotalSale = new EmployeeTotalSale(employeeId, employeeName, totalSalesValueOfMonth);

            empTotalSaleList.add(empTotalSale);
        }

        EmployeeTotalSale emloyeeBest = new EmployeeTotalSale();
        double maxValue = 0.0;

        for (EmployeeTotalSale employee : empTotalSaleList) {

            if (employee.getValue() >= maxValue) {
                maxValue = employee.getValue();
                emloyeeBest = employee;
            }
        }

        return emloyeeBest;
    }

    //Função que faz o agrupamento de clientes por mês
    public static Map<Integer, List<Client>> groupingClientsByMonth(ArrayList<Client> clients) {

        Map<Integer, List<Client>> clientsGroupedByMonth = new HashMap<Integer, List<Client>>();
        for (Iterator it = clients.iterator(); it.hasNext();) {
            Client iClient = (Client) it.next();
            int key = iClient.getBirthDateMonth();
            if (clientsGroupedByMonth.get(key) == null) {
                clientsGroupedByMonth.put(key, new ArrayList<Client>());
            }
            clientsGroupedByMonth.get(key).add(iClient);
        }

        return clientsGroupedByMonth;
    }

    //Função que traz a idade e o dia de nascimento de cada cliente
    public static List<ClientAgeAndDayBirth> getClientsAgeAndDayBirth(ArrayList<Client> clients) {

        ArrayList<ClientAgeAndDayBirth> clientsAgeAndDayBirth = new ArrayList<ClientAgeAndDayBirth>();

        LocalDate todayDate = LocalDate.now();

        for (int i = 0; i < clients.size(); i++) {
            Client cli = (Client) clients.get(i);

            LocalDate birthDate = LocalDate.of(cli.getBirthDateYear(), cli.getBirthDateMonth(), cli.getBirthDateDay());
            int age = Period.between(birthDate, todayDate).getYears();

            ClientAgeAndDayBirth cliAgeDay = new ClientAgeAndDayBirth(cli.getName(), age, cli.getBirthDateDay());

            clientsAgeAndDayBirth.add(cliAgeDay);

        }

        return clientsAgeAndDayBirth;
    }

    //Função que faz o agrupamento das vendas por cliente
    public static Map<Integer, List<Sale>> groupingSalesByClient(ArrayList<Sale> sales) {

        Map<Integer, List<Sale>> saleGroupedByClient = new HashMap<Integer, List<Sale>>();
        for (Iterator it = sales.iterator(); it.hasNext();) {
            Sale iSale = (Sale) it.next();
            int key = iSale.getClientId();
            if (saleGroupedByClient.get(key) == null) {
                saleGroupedByClient.put(key, new ArrayList<Sale>());
            }
            saleGroupedByClient.get(key).add(iSale);
        }

        return saleGroupedByClient;
    }

    //Função que retorna o nome do cliente
    public static String getClientName(int clientId, ArrayList<Client> clients) {

        String clientName = "";

        for (int i = 0; i < clients.size(); i++) {
            Client cli = clients.get(i);

            if (clientId == cli.getId()) {
                clientName = cli.getName();
            }
        }

        return clientName;
    }

    //Função que retorna o nome do Funcionário
    public static String getEmployeeName(int employeeId, ArrayList<Employee> employees) {

        String employeeName = "";

        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);

            if (employeeId == emp.getId()) {
                employeeName = emp.getName();
            }
        }

        return employeeName;
    }

    //Função que retorna o cliente que mais comprou
    public static ClientTotalSale getBestClient(Map<Integer, List<Sale>> salesGroupedByClient, ArrayList<Client> clients) {

        ArrayList<ClientTotalSale> cliTotalSaleList = new ArrayList<ClientTotalSale>();
        for (Map.Entry<Integer, List<Sale>> pairClientIdAndSales : salesGroupedByClient.entrySet()) {
            int clientId = pairClientIdAndSales.getKey();//ID do cliente
            double totalSalesValue = TargetFile.sumSalesValue((ArrayList) pairClientIdAndSales.getValue()); //total vendas

            String clientName = TargetFile.getClientName(clientId, clients);
            
            ClientTotalSale cliTotalSale = new ClientTotalSale(clientId, clientName, totalSalesValue);

            cliTotalSaleList.add(cliTotalSale);
        }

        ClientTotalSale clientBest = new ClientTotalSale();
        double maxValue = 0.0;

        for (ClientTotalSale client : cliTotalSaleList) {

            if (client.getValue() >= maxValue) {
                maxValue = client.getValue();
                clientBest = client;
            }
        }

        return clientBest;
    }

    //Função que traz a lista de funcionário e o valor total de vendas ordenados por nome
    public static List<EmployeeTotalSale> getEmployeesBySaleSortedName(Map<Integer, List<Sale>> saleGroupedByEmployeeOfMonth, ArrayList<Employee> employees) {

        ArrayList<EmployeeTotalSale> empTotalSaleList = new ArrayList<EmployeeTotalSale>();
        for (Map.Entry<Integer, List<Sale>> pairEmployeeIdAndSales : saleGroupedByEmployeeOfMonth.entrySet()) {
            int employeeId = pairEmployeeIdAndSales.getKey();//ID do funcionario
            double totalSalesValueOfMonth = TargetFile.sumSalesValue((ArrayList) pairEmployeeIdAndSales.getValue()); //total vendas do mês

            EmployeeTotalSale empTotalSale = new EmployeeTotalSale(employeeId, TargetFile.getEmployeeName(employeeId, employees), totalSalesValueOfMonth);

            empTotalSaleList.add(empTotalSale);
        }

        Collections.sort(empTotalSaleList, new Comparator<EmployeeTotalSale>() {
            public int compare(EmployeeTotalSale p1, EmployeeTotalSale p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });

        return empTotalSaleList;
    }

    //Função que traz o tempo de trabalho desde o inicio do funcionário na empresa
    public static List<EmployeeTimeContract> getEmployeeTimeContract(ArrayList<Employee> employees) {

        ArrayList<EmployeeTimeContract> employeesTimeContract = new ArrayList<EmployeeTimeContract>();

        LocalDate todayDate = LocalDate.now();

        for (int i = 0; i < employees.size(); i++) {
            Employee emp = (Employee) employees.get(i);

            LocalDate contractDate = LocalDate.of(emp.getContractDateYear(), emp.getContractDateMonth(), emp.getContractDateDay());
            int years = Period.between(contractDate, todayDate).getYears();
            int months = Period.between(contractDate, todayDate).getMonths();
            int days = Period.between(contractDate, todayDate).getDays();

            EmployeeTimeContract emptimeContract = new EmployeeTimeContract(emp.getId(), emp.getName(), years, months, days);

            employeesTimeContract.add(emptimeContract);
        }

        return employeesTimeContract;
    }

    //Função que traz o tempo de trabalho desde o inicio do funcionário na empresa ordenado por tempo
    public static List<EmployeeTime> getEmployeeTimeContractSorted(ArrayList<Employee> employees) {

        ArrayList<EmployeeTime> employeesTimeContract = new ArrayList<EmployeeTime>();

        for (int i = 0; i < employees.size(); i++) {
            Employee emp = (Employee) employees.get(i);

            Calendar myCalendar = new GregorianCalendar(emp.getContractDateYear(), emp.getContractDateMonth(), emp.getContractDateDay());
            Date myDate = myCalendar.getTime();

            long totalTime = myDate.getTime();
            LocalDate contractDate = LocalDate.of(emp.getContractDateYear(), emp.getContractDateMonth(), emp.getContractDateDay());

            EmployeeTime empTime = new EmployeeTime(emp.getId(), emp.getName(), totalTime, emp.getContractDateDay(), emp.getContractDateMonth(), emp.getContractDateYear());

            employeesTimeContract.add(empTime);
        }

        Collections.sort(employeesTimeContract, new Comparator<EmployeeTime>() {
            public int compare(EmployeeTime p1, EmployeeTime p2) {
                return Long.compare(p1.getTotalTime(), p2.getTotalTime());
            }
        });

        return employeesTimeContract;
    }
}
    
    

    

    
    

    

    

    

   

   
