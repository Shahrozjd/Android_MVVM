package com.sterlof.android_mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
     NoteDatabase noteDatabase =  NoteDatabase.getInstance(application);
     noteDao = noteDatabase.noteDao();
     allNotes = noteDao.getAllNodes();
    }

    public void insert(Note note)
    {
        new InsertNodeAsyncTask(noteDao).execute(note);
    }
    public void update(Note note)
    {
        new UpdateNodeAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note)
    {
        new DeleteNodeAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNodes()
    {
        new DeleteAllNodeAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNodeAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public InsertNodeAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }

    }
    private static class UpdateNodeAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public UpdateNodeAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }

    }
    private static class DeleteNodeAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public DeleteNodeAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }

    }
    private static class DeleteAllNodeAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public DeleteAllNodeAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteAllNodes();
            return null;
        }

    }
}
