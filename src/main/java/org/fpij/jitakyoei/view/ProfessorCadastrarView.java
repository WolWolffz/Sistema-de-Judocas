package org.fpij.jitakyoei.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.ProfessorEntidade;
import org.fpij.jitakyoei.view.forms.ProfessorForm;
import org.fpij.jitakyoei.view.gui.ProfessorCadastrarPanel;

public class ProfessorCadastrarView implements ViewComponent {
	ProfessorCadastrarPanel gui;
	private ProfessorForm professorForm;
	private AppFacade facade;
	private MainAppView parent;
	
	public ProfessorCadastrarView(MainAppView parent){
		this.parent = parent;
		gui = new ProfessorCadastrarPanel();
		gui.getCancelar().addActionListener(new CancelarActionHandler());
		gui.getCadastrar().addActionListener(new CadastrarActionHandler());
		professorForm = new ProfessorForm(gui.getProfessorPanel());
	}
	
	@Override
	public JPanel getGui() {
		return gui;
	}

	@Override
	public void registerFacade(AppFacade fac) {
		this.facade = fac;
	}
	
	/**
	 * Classe interna responsável por responder aos cliques no botão "Cadastrar".
	 * 
	 * @author Aécio
	 */
	public class CadastrarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try{
				Professor professor = professorForm.getProfessor();
				List<Entidade> entidades = professorForm.getEntidadesList();
				
				List<ProfessorEntidade> relacionamentos = new ArrayList<ProfessorEntidade>();
				for (Entidade entidade : entidades) {
					relacionamentos.add(new ProfessorEntidade(professor, entidade));
				}

				if (professor.getFiliado().getNome() == null || professor.getFiliado().getNome().isEmpty()) {
					JOptionPane.showMessageDialog(gui, "Erro: O nome do aluno não pode ser vazio!", 
												  "Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return; 
				}
	
				if (professor.getFiliado().getCpf() == null || professor.getFiliado().getCpf().isEmpty()) {
					JOptionPane.showMessageDialog(gui, "Erro: O CPF do aluno não pode ser vazio!", 
												  "Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return; 
				}
	
				if (professor.getFiliado().getEmail() == null || professor.getFiliado().getEmail().isEmpty()) {
					JOptionPane.showMessageDialog(gui, "Erro: O e-mail do aluno não pode ser vazio!", 
												  "Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return;
				}
	
				if (professor.getFiliado().getDataNascimento() == null) {
					JOptionPane.showMessageDialog(gui, "Erro: A data de nascimento do aluno não pode ser vazia!", 
												  "Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return; 
				}
				facade.createProfessor(professor);
				facade.createProfessorEntidade(relacionamentos);
				JOptionPane.showMessageDialog(gui, "Professor cadastrado com sucesso!");
				parent.removeTabPanel(gui);			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Classe interna responsável por responder aos cliques no botão "Cancelar".
	 * 
	 * @author Aécio
	 */
	public class CancelarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.removeTabPanel(gui);
		}
	}
}
