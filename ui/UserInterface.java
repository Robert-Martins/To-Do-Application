package ui;

import controllers.CommentController;
import controllers.JobController;
import controllers.UserController;
import controllers.WorkspaceController;
import models.User;
import models.Workspace;

import javax.swing.*;

public class UserInterface {

    private UserController userController = new UserController();

    private WorkspaceController workspaceController = new WorkspaceController();

    private JobController jobController = new JobController();

    private CommentController commentController = new CommentController();

    public UserInterface(){}

    public void application(){
        var user = userController.getUser();
        if(user.getCreatedAt() == null){
            JOptionPane.showMessageDialog(null, "Parece que é sua primeira vez utilizando nosso sistema, pressione ok para inserir seu nome");
            String name = JOptionPane.showInputDialog("Digite seu nome:");
            user = userController.initializeUser(name);
        }
        var option = mainMenu(user);
        if(option == 1)
            workspacesMenu(user);
        if(option == 2)
            workspaceRegisterMenu();
    }

    public int mainMenu(User user){
        String firstMenuOption = user.getWorkspaces() == null || user.getWorkspaces().isEmpty() ? "Você ainda não possui workspaces" : "1 - Acessar workspaces";
        String secondMenuOption = user.getWorkspaces() != null && user.getWorkspaces().size() > 10 ? "Você possui o número máximo de workspaces" : "2 - Criar novo workspace";
        return Integer.parseInt(JOptionPane.showInputDialog("Olá "+user.getName()+"!\n"+
                "Qual menu deseja acessar?\n"+
                firstMenuOption+"\n"+
                secondMenuOption+"\n"+
                "\n"+
                "0 - Encerrar programa\n"+
                "\n")
        );
    }

    public int workspacesMenu(User user){
        return Integer.parseInt(JOptionPane.showInputDialog("Selecione uma opção\n"+
                workspacesMenuOptions(user)+
                "10 - Voltar para o menu principal"+
                "11 - Encerrar programa"));
    }

    public Workspace workspaceRegisterMenu(){
        System.out.println("fdfdfdfd");
    }

    public String workspacesMenuOptions(User user){
        var workspaces = user.getWorkspaces();
        String menuOptions = "Seus workspaces:\n\n";
        for(int count = 0; count <= workspaces.size(); count++){
            menuOptions += count + 1 + " - "+workspaces.get(count).getName();
        }
        return menuOptions;
    }

}
