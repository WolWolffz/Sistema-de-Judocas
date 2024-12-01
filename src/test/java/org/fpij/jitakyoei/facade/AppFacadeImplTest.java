package org.fpij.jitakyoei.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import org.fpij.jitakyoei.business.AlunoBO;
import org.fpij.jitakyoei.business.AlunoBOImpl;
import org.fpij.jitakyoei.business.EntidadeBO;
import org.fpij.jitakyoei.business.EntidadeBOImpl;
import org.fpij.jitakyoei.business.ProfessorBO;
import org.fpij.jitakyoei.business.ProfessorBOImpl;
import org.fpij.jitakyoei.business.ProfessorEntidadeBO;
import org.fpij.jitakyoei.business.ProfessorEntidadeBOImpl;
import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.dao.DAO;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.fpij.jitakyoei.view.AppView;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.db4o.ObjectSet;
import com.db4o.ext.ExtObjectContainer;
import com.db4o.ext.StoredClass;
import com.db4o.query.Query;

import org.junit.Assert.*;

public class AppFacadeImplTest {
    private AppView mockView;
	private Aluno mockAluno;
	private ProfessorBO mockProfessorBO;
	private EntidadeBO mockEntidadeBO;
	private ProfessorEntidadeBO mockProfessorEntidadeBO;

    protected static ExtObjectContainer db = DatabaseManager.getConnection();
    
    private AppFacadeImpl appFacade;
    private AppFacadeImpl mockappFacade;

    @Before
    public void setUp(){
        mockView = mock(AppView.class);
        mockAluno = mock(Aluno.class);
        mockProfessorBO = mock(ProfessorBO.class);
		mockEntidadeBO = mock(EntidadeBO.class);
		mockProfessorEntidadeBO = mock(ProfessorEntidadeBO.class);
        
        appFacade = new AppFacadeImpl(mockView);
        mockappFacade = mock(AppFacadeImpl.class);
    }

    //testes aluno
    //TODO: teste não necessário/redundante, necessário reavaliar
    // @Test
    // public void testCreateAluno(){
    //     appFacade.createAluno(new Aluno());

    //     assertEquals(1, appFacade.listAlunos().size());
    // }

    // @Test
    // public void testUpdateAluno(){
    //     Aluno oldAluno = new Aluno();
    //     Aluno newAluno = new Aluno();

    //     appFacade.createAluno(oldAluno);

    //     appFacade.updateAluno(newAluno);
    // }

    @Test
    public void testSearchAluno(){
        //apagar todo os dados do banco de teste antes de realizar os testes
        DatabaseManager.deleteall();

        Aluno aluno = new Aluno();
        Aluno aluno2 = new Aluno();

        appFacade.createAluno(aluno);

        assertSame(aluno, appFacade.searchAluno(aluno).get(0));
        assertNotSame(aluno2, appFacade.searchAluno(aluno).get(0));
    }

    @Test
    public void testListAluno(){
        //apagar todo os dados do banco de teste antes de realizar os testes
        DatabaseManager.deleteall();

        appFacade.createAluno(new Aluno());
        appFacade.createAluno(new Aluno());

        assertEquals(2, appFacade.listAlunos().size());
    }

    
    //testes professor
    //TODO: teste não necessário/redundante, necessário reavaliar
    // @Test
    // public void testCreateProfessor(){
    //     Professor professor = new Professor();
    //     appFacade.createProfessor(professor);
    // }

    //TODO: precisa ser refatorado pois não funciona/testa do modo que deveria por conta do funcionamento da classe
    // @Test
    // public void testUpdateProfessor(){
    //     //apagar todo os dados do banco de teste antes de realizar os testes
    //     DatabaseManager.deleteall();

    //     Professor oldProfessor = new Professor();
    //     Professor newProfessor = new Professor();

    //     appFacade.createProfessor(oldProfessor);
    //     assertSame(oldProfessor, appFacade.searchProfessor(oldProfessor).get(0));


    //     appFacade.updateProfessor(newProfessor);

    //     System.out.println("TESTEESTESTESTESTESTSTEXZTE");
    //     System.out.println(newProfessor);
    //     System.out.println(appFacade.searchProfessor(newProfessor).get(0));

    //     assertSame(newProfessor, appFacade.searchProfessor(newProfessor).get(0));
    // }

    @Test
    public void testSearchProfessor(){
        //apagar todo os dados do banco de teste antes de realizar os testes
        DatabaseManager.deleteall();

        Professor professor = new Professor();
        Professor professor2 = new Professor();

        appFacade.createProfessor(professor);

        // System.out.println(professor.hashCode());
        // System.out.println(professor2.hashCode());
        // System.out.println(appFacade.searchProfessor(professor).get(0).hashCode());

        assertSame(professor, appFacade.searchProfessor(professor).get(0));
        assertNotSame(professor2, appFacade.searchProfessor(professor).get(0));
    }

    @Test
    public void testListProfessores(){
        //apagar todo os dados do banco de teste antes de realizar os testes
        DatabaseManager.deleteall();

        appFacade.createProfessor(new Professor());
        appFacade.createProfessor(new Professor());

        assertEquals(2, appFacade.listProfessores().size());
    }

    //testes entidade

    //TODO: teste não necessário/redundante, necessário reavaliar
    // @Test
    // public void testCreateEntidade(){
    //     Entidade entidade = new Entidade();
    //     appFacade.createEntidade(entidade);
    // }

    // @Test
    // public void testUpdateEntidade(){
    //     Entidade oldEntidade = new Entidade();
    //     Entidade newEntidade = new Entidade();

    //     appFacade.createEntidade(oldEntidade);

    //     appFacade.updateEntidade(newEntidade);
    // }

    @Test
    public void testSearchEntidade(){
        //apagar todo os dados do banco de teste antes de realizar os testes
        DatabaseManager.deleteall();

        Entidade entidade = new Entidade();
        Entidade entidade2 = new Entidade();

        appFacade.createEntidade(entidade);

        assertSame(entidade, appFacade.searchEntidade(entidade).get(0));
        assertNotSame(entidade2, appFacade.searchEntidade(entidade).get(0));
    }

    @Test
    public void testListEntidades(){
        //apagar todo os dados do banco de teste antes de realizar os testes
        DatabaseManager.deleteall();

        appFacade.createEntidade(new Entidade());
        appFacade.createEntidade(new Entidade());

        assertEquals(2, appFacade.listEntidade().size());
    }
}
