package ui;

import controllers.CommentController;
import controllers.JobController;
import controllers.UserController;
import controllers.WorkspaceController;
import models.Job;
import models.User;
import models.Workspace;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class UserInterface {

    private final UserController userController = new UserController();

    private final WorkspaceController workspaceController = new WorkspaceController();

    private final JobController jobController = new JobController();

    private final CommentController commentController = new CommentController();

    public UserInterface(){}

    public void application(){
        var user = userController.getUser();
        if(user.getCreatedAt() == null){
            JOptionPane.showMessageDialog(null, "Parece que é sua primeira vez utilizando nosso sistema, pressione ok para inserir seu nome");
            String name = JOptionPane.showInputDialog("Digite seu nome:\n");
            userController.initializeUser(name);
        }
        mainMenu();
    }

    private void mainMenu(){
        var user = userController.getUser();
        String firstMenuOption = user.getWorkspaces() == null || user.getWorkspaces().isEmpty() ? "Você ainda não possui workspaces" : "1 - Acessar workspaces";
        String secondMenuOption = user.getWorkspaces() != null && user.getWorkspaces().size() > 10 ? "Você possui o número máximo de workspaces" : "2 - Criar novo workspace";
        var option = Integer.parseInt(JOptionPane.showInputDialog("Olá "+user.getName()+"!\n"+
                "Qual menu deseja acessar?\n"+
                firstMenuOption+"\n"+
                secondMenuOption+"\n"+
                "\n"+
                "0 - Encerrar programa\n"+
                "\n")
        );
        if(option == 1)
            workspacesMenu();
        if(option == 2)
            workspaceRegisterMenu();
        if(option == 0)
            systemShutdown();
        if(option < 0 || option > 2) {
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n\n");
            mainMenu();
        }
    }

    private void workspacesMenu(){
        var user = userController.getUser();
        var option = Integer.parseInt(JOptionPane.showInputDialog("Selecione uma opção\n"+
                workspacesMenuOptions(user.getWorkspaces())+
                "\n"+
                "10 - Voltar para o menu principal\n"+
                "11 - Encerrar programa\n"));
        if(option >= 0 && option <= 9){
            try{
                var workspace = user.getWorkspaces().get(option);
                workspaceMenu(workspace.getUuid());
            }
            catch (IndexOutOfBoundsException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Digite uma opção válida\n\n");
                workspacesMenu();
            }
        }
        if(option == 10)
            mainMenu();
        if(option == 11)
            systemShutdown();
        if(option > 11 || option < 0){
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n");
            workspacesMenu();
        }
    }

    private String workspacesMenuOptions(List<Workspace> workspaceList){
        var workspaces = workspaceList;
        String menuOptions = "Seus workspaces:\n\n";
        for(int count = 0; count <= workspaces.size(); count++)
            menuOptions += count + " - "+workspaces.get(count).getName()+"\n";
        return menuOptions;
    }

    private void workspaceRegisterMenu(){
        var user = userController.getUser();
        if(user.getWorkspaces() != null && user.getWorkspaces().size() > 10){
            JOptionPane.showMessageDialog(null, "Número máximo de workspaces cadastrados\n\n");
            mainMenu();
        }
        String name = JOptionPane.showInputDialog("Digite o nome do workspace: \n");
        String description = JOptionPane.showInputDialog("Digite a descrição do workspace: \n");
        var workspace = workspaceController.createWorkspace(name, description);
        workspaceMenu(workspace.getUuid());
    }

    private void workspaceUpdateMenu(UUID uuid){
        String name = JOptionPane.showInputDialog("Digite o nome do workspace: \n");
        String description = JOptionPane.showInputDialog("Digite a descrição do workspace: \n");
        workspaceController.updateWorkspace(uuid, name, description);
        JOptionPane.showMessageDialog(null, "Workspace atualizado\n\n");
        workspaceMenu(uuid);
    }

    private void workspaceMenu(UUID uuid){
        var user = userController.getUser();
        var workspace = workspaceController.getWorkspace(uuid);
        var option = Integer.parseInt(JOptionPane.showInputDialog(
           workspace.getName()+"\n\n"+
           "Workspaces jobs: \n"+
           workspaceMenuJobsOption(workspace.getJobs())+
           "\n"+
           "10 - Criar novo Job"+
           "11 - Editar workspace"+
           "12 - Voltar para o menu principal"+
           "13 - Encerrar programa"
        ));
        if(option >= 0 && option <= 9){

        }
        if(option == 10)
            jobRegisterMenu();
        if(option == 11)
            workspaceUpdateMenu(uuid);
        if(option == 12)
            mainMenu();
        if(option == 13)
            systemShutdown();
        if(option > 13 || option < 0){
            JOptionPane.showMessageDialog(null, "Digite uma opção válida\n");
            workspaceMenu(uuid);
        }
    }

    private String workspaceMenuJobsOption(List<Job> jobList){
        var jobs = jobList;
        Job job;
        String menuOptions = "Seus workspaces:\n\n";
        for(int count = 0; count <= jobs.size(); count++){
            job = jobs.get(count);
            menuOptions += count+ " - "+job.getName()+" - Status: "+job.getStatus()+"\n";
        }
        return menuOptions;
    }

    private void jobRegisterMenu(){

    }

    private void jobUpdateMenu(){

    }

    private void systemShutdown(){
        var user = userController.getUser();
        JOptionPane.showMessageDialog(null, "O sistema será encerrado\n"+"Obrigado "+user.getName()+"!\n\n");
        System.exit(0);
    }

}
